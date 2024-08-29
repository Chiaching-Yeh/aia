package manage.security;

import com.systex.support.FunctionLogSupport;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

public class CustomWebAuthenticationDetailsSource extends WebAuthenticationDetailsSource {

    public CustomWebAuthenticationDetailsSource() {
        super();
    }

    @Override
    public WebAuthenticationDetails buildDetails(HttpServletRequest context) {
    	
    	FunctionLogSupport.start("CustomWebAuthenticationDetailsSource.buildDetails");
    	FunctionLogSupport.end("CustomWebAuthenticationDetailsSource.buildDetails");
    	
        return new CustomWebAuthenticationDetails(context);
    }

}
