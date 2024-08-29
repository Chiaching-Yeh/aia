package manage.security;

import com.systex.support.FunctionLogSupport;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.util.Objects;

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

    public static final long serialVersionUID = 52L;
    public static final String CaptchaAnswerAttrName = "@_CaptchaAnswer_@";

    private final String verifyCode;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        FunctionLogSupport.start("CustomWebAuthenticationDetails.CustomWebAuthenticationDetails");
        verifyCode = Objects.toString(request.getParameter("verifyCode"), "");
        System.out.println("verifyCode: " + verifyCode);
        FunctionLogSupport.end("CustomWebAuthenticationDetails.CustomWebAuthenticationDetails");
    }

    public String getVerifyCode() {
        return verifyCode;
    }

}
