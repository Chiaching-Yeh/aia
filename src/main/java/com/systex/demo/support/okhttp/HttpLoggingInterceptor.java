package com.systex.demo.support.okhttp;

import okhttp3.*;
import okio.Buffer;
import okio.BufferedSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static java.net.HttpURLConnection.HTTP_NOT_MODIFIED;
import static java.net.HttpURLConnection.HTTP_NO_CONTENT;
import static okhttp3.internal.http.StatusLine.HTTP_CONTINUE;

/**
 * An OkHttp interceptor that logs request and response information. Can be applied as an
 * {@linkplain OkHttpClient#interceptors() application interceptor} or as a
 * {@linkplain OkHttpClient#networkInterceptors() network interceptor}.
 * <p>
 * The log levels written are as follows:
 * <p>
 * {@code DEBUG}: Logs request and response lines and their respective headers and bodies
 * (if present).
 * <pre>{@code
 *  --> POST /greeting http/1.1
 *  Host: example.com
 *  Content-Type: plain/text
 *  Content-Length: 3
 *
 *  Hi?
 *  --> END POST
 *
 *  <-- 200 OK (22ms)
 *  Content-Type: plain/text
 *  Content-Length: 6
 *
 *  Hello!
 *  <-- END HTTP
 * }</pre>
 * <p>
 * {@code INFO}: Logs request and response lines and their respective headers.
 * <pre>{@code
 *  --> POST /greeting http/1.1
 *  Host: example.com
 *  Content-Type: plain/text
 *  Content-Length: 3
 *  --> END POST
 *
 *  <-- 200 OK (22ms)
 *  Content-Type: plain/text
 *  Content-Length: 6
 *  <-- END HTTP
 * }</pre>
 * <p>
 * Other log levels, such as {@code WARN} and {@code ERROR}, are ignored.
 */
public class HttpLoggingInterceptor implements Interceptor {

    public static final String DEFAULT_LOGGER_NAME = "okhttp3.logging.wire";

    private static final Charset UTF8 = StandardCharsets.UTF_8;
    private static final Logger DEFAULT_LOGGER = LoggerFactory.getLogger(DEFAULT_LOGGER_NAME);

    private final Logger logger;
    private final long peekBodySize;

    public HttpLoggingInterceptor() {
        this(DEFAULT_LOGGER);
    }

    public HttpLoggingInterceptor(Logger logger) {
        this(logger, Long.MAX_VALUE);
    }

    public HttpLoggingInterceptor(long peekBodySize) {
        this(DEFAULT_LOGGER, peekBodySize);
    }

    /**
     * Creates an Slf4j logging interceptor instance.
     *
     * @param logger       The logger to be used
     * @param peekBodySize The size to be logged when reading the response body. Defaults
     *                     to {@code Long.MAX_VALUE}
     */
    public HttpLoggingInterceptor(Logger logger, long peekBodySize) {
        if (logger == null)
            throw new IllegalArgumentException("Can't use null logger");
        if (peekBodySize < 0)
            throw new IllegalArgumentException("peekBodySize can't be negative");
        this.logger = logger;
        this.peekBodySize = peekBodySize;
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        boolean logBody = logger.isDebugEnabled();
        boolean logHeaders = logBody || logger.isInfoEnabled();

        Request request = chain.request();

        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;

        Connection connection = chain.connection();
        Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;

        logger.info("--> {} {} {}", request.method(), request.url(), protocol);

        if (logHeaders) {
            if (hasRequestBody) {
                if (requestBody.contentType() != null)
                    logger.info("Content-Type: {}", requestBody.contentType());

                if (requestBody.contentLength() != -1)
                    logger.info("Content-Length: {}", requestBody.contentLength());
            }

            Headers headers = request.headers();
            for (int i = 0; i < headers.size(); i++) {
                String name = headers.name(i);
                if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name))
                    logger.info("{}: {}", name, headers.value(i));
            }

            if (!logBody || !hasRequestBody)
                logger.info("--> END {}", request.method());
            else if (bodyEncoded(request.headers()))
                logger.info("--> END {} (encoded body omitted)", request.method());
            else {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);

                Charset charset = UTF8;
                MediaType contentType = requestBody.contentType();
                if (contentType != null)
                    charset = contentType.charset(UTF8);

                logger.debug(""); // new line
                if (isPlaintext(buffer)) {
                    logger.debug(buffer.readString(charset));
                    logger.debug("--> END {} ({}-byte body)", request.method(), requestBody.contentLength());
                } else {
                    logger.debug("--> END {} (binary {}-byte body omitted)", request.method(), requestBody.contentLength());
                }
            }
        }

        long startNs = System.nanoTime();
        Response response;
        try {
            response = chain.proceed(request);
        } catch (Exception e) {
            logger.info("<-- HTTP FAILED: " + e);
            throw e;
        }

        long elapsedNs = System.nanoTime() - startNs;
        long tookMs = elapsedNs / 1_000_000;

        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";

        logger.info("<-- {} {} {} (took {} ms{})",
                response.code(), response.message(), response.request().url(), tookMs,
                !logHeaders ? ", " + bodySize + " body" : "");

        if (logHeaders) {
            Headers headers = response.headers();
            for (int i = 0; i < headers.size(); i++) {
                logger.info("{}: {}", headers.name(i), headers.value(i));
            }

            if (!logBody || !hasBody(response)) {
                logger.info("<-- END HTTP");
            } else if (bodyEncoded(headers)) {
                logger.info("<-- END HTTP (encoded body omitted)");
            } else {
                BufferedSource source = responseBody.source();
                source.request(peekBodySize);
                Buffer buffer = source.getBuffer();

                Charset charset = UTF8;
                MediaType contentType = responseBody.contentType();
                if (contentType != null)
                    charset = contentType.charset(UTF8);

                if (!isPlaintext(buffer)) {
                    logger.debug("");
                    logger.debug("<-- END HTTP (binary {}-byte body omitted)", contentLength);
                    return response;
                }

                if (contentLength != 0) {
                    logger.debug("");
                    logger.debug(buffer.clone().readString(charset));
                }

                logger.debug("<-- END HTTP ({}-byte body)", contentLength);
            }
        }

        return response;
    }

    /**
     * Returns true if the body in question probably contains human readable text. Uses a small
     * sample of code points to detect unicode control characters commonly used in binary file
     * signatures.
     */
    protected static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) break;
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) return false;
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

    /**
     * Returns true if the response must have a (possibly 0-length) body. See RFC 7231.
     */
    protected static boolean hasBody(Response response) {
        // HEAD requests never yield a body regardless of the response headers.
        if (response.request().method().equals("HEAD")) return false;

        int responseCode = response.code();
        if ((responseCode < HTTP_CONTINUE || responseCode >= 200)
                && responseCode != HTTP_NO_CONTENT
                && responseCode != HTTP_NOT_MODIFIED) return true;

        // If the Content-Length or Transfer-Encoding headers disagree with the response code, the
        // response is malformed. For best compatibility, we honor the headers.
        if (contentLength(response) != -1
                || "chunked".equalsIgnoreCase(response.header("Transfer-Encoding"))) return true;

        return false;
    }

    protected static long contentLength(Response response) {
        return contentLength(response.headers());
    }

    protected static long contentLength(Headers headers) {
        return stringToLong(headers.get("Content-Length"));
    }

    private static long stringToLong(String s) {
        if (s == null) return -1;
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}