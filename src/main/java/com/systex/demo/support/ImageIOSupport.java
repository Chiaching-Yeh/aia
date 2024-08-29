package com.systex.support;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.springframework.web.multipart.MultipartFile;
import com.systex.support.file.LimitedSizeInputStreamFile;
import com.systex.support.validator.FileValidator;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
public final class ImageIOSupport {

    public static int ImageWidthThreshold = 1000;

    public static String getOutputFormatFromMimeType(String mimeType) {
        String outFormat = "";
        switch (mimeType) {
            case "image/png":
                outFormat = "PNG";
                break;
            case "image/jpeg":
                outFormat = "JPG";
                break;
            case "image/jpg":
                outFormat = "JPG";
                break;
            case "image/gif":
                outFormat = "GIF";
                break;
            default:
                outFormat = "";
                break;
        }
        return outFormat;
    }


    /**
     * 回傳縮圖的檔名，如果回傳空字串，表示有問題，或不縮圖
     *
     * @throws IOException
     */
    public static String thumbnailsnew(
											MultipartFile multipartFile,
											String number,
											String ext,
											FileValidator uploadDirPath) throws IOException {
    	
        BufferedImage bufferedImage = null;
        try (
    		InputStream inputStream = new LimitedSizeInputStreamFile(multipartFile.getInputStream(), 8192000);
        ) {
             bufferedImage = ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        log.info("11");
        
        int imageWidth = bufferedImage.getWidth();
        int imageHeight = bufferedImage.getHeight();

        log.info("12");
        
        //if (imageWidth > ImageWidthThreshold) {
        	
        log.info("multipartFile.getContentType(): {}", multipartFile.getContentType());
        
        String mimeType = multipartFile.getContentType();
        
        if (multipartFile.getContentType().equals("png") || multipartFile.getContentType().equals("PNG")) {
        	mimeType = "image/png";
        } else if (multipartFile.getContentType().equals("jpeg") || multipartFile.getContentType().equals("JPEG")) {
        	mimeType = "image/jpeg";
        } else if (multipartFile.getContentType().equals("jpg") || multipartFile.getContentType().equals("JPG")){
        	mimeType = "image/jpg";
        } else if (multipartFile.getContentType().equals("gif") || multipartFile.getContentType().equals("GIF")){
        	mimeType = "image/gif";
        }

        String outputFormat = getOutputFormatFromMimeType(mimeType);
              
        
        if (outputFormat.equals("")) {
            log.error("不支援的圖型outputFormat");
            return "";
        } else {
            String imageMagickName = number + "_small" + "." + ext;
            
            log.info("13");
            
            log.info("imageMagickName: {}/{}", uploadDirPath.getSPath(), imageMagickName);
            
            FileValidator imageMagickFile =  new FileValidator(uploadDirPath, imageMagickName);
            
            log.info("14");

            int scaleWidth = ImageWidthThreshold;
            int scaleHeight = (imageHeight * ImageWidthThreshold) / imageWidth;

            log.info("15");
            
            Thumbnails.of(bufferedImage)
                    .size(scaleWidth, scaleHeight)
                    .outputFormat(outputFormat)
                    .toFile(imageMagickFile.getFile());
            
            log.info("16");
            
            return imageMagickName;
        }
        //} else {
            //return "";
       //}

    }

    private ImageIOSupport() {
        super();
    }

}
