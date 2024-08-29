package manage.security;

import com.systex.model.AptreeModel;
import com.systex.model.DepartmentModel;
import com.systex.model.UserModel;
import com.systex.model.table.Tree;
import com.systex.service.*;
import com.systex.support.Base64Support;
import com.systex.support.FunctionLogSupport;
import com.systex.support.IPSupport;
import com.systex.support.LocalDateTimeSupport;
import com.systex.support.authentication.GenericResponse;
import com.systex.support.exception.ApTreeRootNotExistException;
import com.systex.support.exception.BadVerifyCodeException;
import com.systex.support.exception.DepartmentNotExistException;
import com.systex.support.user.AuthenticationTokenUser;
import com.systex.support.validator.ESAPIValidator;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.jboss.aerogear.security.otp.Totp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Value("${app.config.path}")
    private String ROOTPATH_CONFIG;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected UserService userService;
    
    @Autowired
    protected TOTPService TOTPService;

    @Autowired
    protected DepartmentService departmentService;
    
    @Autowired
    protected LoginLogService loginLogService;

    @Autowired
    protected LoginSSOService loginSSOService;

    @Autowired
    protected AptreeService aptreeService;

    @Autowired
    protected DepManagerService depManagerService;

    @Autowired
    protected ApPurviewService apPurviewService;

    @Autowired
    protected AuthorityPathService authorityPathService;
    
    @Autowired
    protected EIPService eipService;
    
    @Autowired
    protected SendMailService sendMailService;

    public CustomAuthenticationProvider() {
        super();
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	
    	FunctionLogSupport.start("CustomAuthenticationProvider.authenticate");
		
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String clientIP = ESAPIValidator.setValidator("clientIP",  IPSupport.getClientIp(request),  "IPAddress", 20, false);

        CustomWebAuthenticationDetails details = (CustomWebAuthenticationDetails) authentication.getDetails();
        String verifyCode = details.getVerifyCode();
        
        String answer = (String) request.getSession(false).getAttribute(CustomWebAuthenticationDetails.CaptchaAnswerAttrName);
        
        System.out.println("answer: " + answer);
        
        System.out.println("verifyCode: " + verifyCode);
        
        String credentials = Objects.toString(authentication.getCredentials(), "");
      
        String username = ESAPIValidator.setValidator("username", Objects.toString(authentication.getName(), ""),  "SafeStringUserN", 100, false);
        
        request.getSession().setAttribute("clientIP", clientIP);
        request.getSession().setAttribute("userName", username);
        
        System.out.println("username: " + username);

        Optional<UserModel> userOpt = userService.findByMuid(username);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date date = new Date();
    	System.out.println("Login Time: " + formatter.format(date));
    	System.out.println("Login IP: " + clientIP);
        
    	Long IPCount = TOTPService.listByIP(clientIP);
    	
    	if(IPCount == 0) {
    		
    		if (userOpt.isPresent()) {
    			
    			UserModel userDetails = userOpt.get();
    		
    			try {
    			
    				if(userDetails.isISUSING2FA() && userDetails.getSN() != null && !"".equals(userDetails.getSN() )) {
	        		
	    				//log.info("Message: {}" , userService.generateQRUrl(userDetails));
    					System.out.println("sn: " + userDetails.getSN());
	    				
	    				//寄信待補
	    				
		                Totp totp = new Totp(userDetails.getSN());
		                if (verifyCode == null || "".equals(verifyCode) || !totp.verify(verifyCode)) {
		                	throw new BadVerifyCodeException("OTP不正確");
		                }
	                
		            }else {
		            	
	    				GenericResponse userResponse = userService.modifyUser2FA(true, userDetails);
	    				
		        		System.out.println("message: " + userResponse.getMessage());
		        		
		        		throw new BadVerifyCodeException("OTP未綁定");
		    	        		
		        		//寄信待補
		        		
		            }
    				
				} catch (UnsupportedEncodingException e) {
					log.info("TOTP QRCode ERROR: {}", e.toString());
					throw new BadVerifyCodeException("OTP發生錯誤");
				}
	            	
    		
    		 }
    		
    	}else {

	        if (verifyCode == null || "".equals(verifyCode) || !verifyCode.equals(answer)) {
	            throw new BadVerifyCodeException("驗證碼不正確");
	        }
        
    	}
    	
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException(username + " 帳號不存在");
        }

        UserModel userDetails = userOpt.get();     

        // 記錄嘗試登入的時間
        userDetails.setLASTLOGINDATE(LocalDateTimeSupport.parse(LocalDateTime.now().toString()));
        userService.updateLastLoginDateByMuid(username);

        // 載入 User 的單位
        Optional<DepartmentModel> departmentOpt = departmentService.findByID(userDetails.getPARENTID());
        if (departmentOpt.isPresent()) {
            userDetails.setDepartment(departmentOpt.get());
        } else {
            userDetails.setDepartment(null);
            throw new DepartmentNotExistException("該User的所屬部門不存在");
        }

        //載入第四層單位DN及ID
        String unitDN = userDetails.getUNITDN();
        String[] arrayUnitDN = userDetails.getUNITDN().split(",");
        if ( arrayUnitDN.length > 2 ) {
            for ( int i=0; i<arrayUnitDN.length-2; i++ ) {
                unitDN = unitDN.substring(unitDN.indexOf(",")+1);
            }
        }

        Optional<DepartmentModel> departmentOptDN = departmentService.findByUnitDN(unitDN);
        Long orgID = departmentOptDN.get().getID();
        
        //登入者單位
        String transName=departmentService.findAllnameByDN(userDetails.getUNITDN());
        userDetails.setCHINESETITLE(transName);
        //登入者姓名
        userDetails.setCNNAME(Base64Support.decode(userDetails.getCN()));

        //載入user role
        userDetails.setRole("user");
        userDetails.setRoleName("上稿人員");
//        Long depCount = depManagerService.manageger(unitDN,userDetails.getDN());
//        if ( depCount > 0 ) {
//            userDetails.setRole("dep");
//            userDetails.setRoleName("網站管理者");
//        }
        
        Long rootCount = apPurviewService.countByApListDNAndAciUserDN("ou=ap_root", userDetails.getDN());
        if ( rootCount > 0 ) {
            userDetails.setRole("admin");
            orgID = Long.valueOf("0");
            userDetails.setRoleName("系統管理者");
        }
        if ( userDetails.getMUID().equals("administrator") ) {
            userDetails.setRole("super");
            orgID = Long.valueOf("0");
            userDetails.setRoleName("廠商管理者");
        }
       
        String roleName = userDetails.getRoleName();
        
        roleName = ESAPIValidator.setValidator("roleName", roleName,  "SafeStringChinese", 100, false);
        
        request.getSession().setAttribute("roleName", roleName);

        userDetails.setOrgId(orgID);
        userDetails.setOrgUnitDN(unitDN);
        
        // 這邊可以檢查其他帳號狀況
        if (! userDetails.isEnabled()) {
        	loginLogService.create("failure", userDetails);
            throw new DisabledException("帳號尚未啟用，或是停用中");
        }
        
        if (! userDetails.isAccountNonLocked()) {
        	loginLogService.create("failure", userDetails);
            throw new LockedException("密碼錯誤次數超過三次，帳號鎖定中");
        }
       
        boolean passed = false;
        if(credentials.equals(userDetails.getPRESETPWD())){
        	passed = true;
        }else {
        	passed = passwordEncoder.matches(credentials, userDetails.getPN());

            /*
        	if( userDetails.getDN().indexOf("ou=315000000H,ou=organization") != -1 && !username.equals("administrator") ){
        	    passed = userService.authenticate(userDetails, username, credentials);
        	    System.out.println("Error Msg: " + userService.getErrorMsg());
        	}else {

             */
            //System.out.println("sha: " + loginSSOService.getSHA256StrJava(credentials));
            if(!passed &&
                userDetails.getPASSWD() != null && !userDetails.getPASSWD().isBlank() &&
                loginSSOService.getSHA256StrJava(credentials).equals(userDetails.getPASSWD())) {
              passed = true;
            }
        	//}
        }
        
        if (!passed) {
            // 增加密碼不正確次數
            int failedNumber = userDetails.getFAILEDPWDNUMBER();
            userDetails.setFAILEDPWDNUMBER(failedNumber + 1);
            userService.updateFailedPwdNumberByMuid(failedNumber + 1, username);
            if (failedNumber >= 3) {
                userDetails.setISLOCKEDOUT(true);
                userService.updateIsLockedOutByMuid(true, username);
            }

            loginLogService.create("failure", userDetails);
            throw new BadCredentialsException("密碼不正確");
        } else {
        	userService.updateFailedPwdNumberByMuid(0, username);
        	userService.updateIsLockedOutByMuid(false, username);
        }

        // 故意清除密碼欄位，不要存在 memory 中
        userDetails.eraseCredentials();

        loginLogService.create("success", userDetails);

        // ckeditor控制權限，不讓使用者直接執行/ckfinder.html
        request.getSession().setAttribute("CKFinder_UserRole", "admin");

        Optional<AptreeModel> rootOpt = aptreeService.findByID(1L);
        if (rootOpt.isEmpty()) {
            throw new ApTreeRootNotExistException("找不到應用系統設定根節點");
        }
        AptreeModel root = rootOpt.get();

        long start = System.currentTimeMillis();
        Tree<AptreeModel> authorityTreeModel = authorityPathService.build(root, userDetails,"Y", "ou=platform,ou=ap_root", "");
        userDetails.setAuthorityModel(authorityTreeModel);
        long end = System.currentTimeMillis();
        log.info("{} seconds.", (end - start) / 1000);
        
        FunctionLogSupport.end("CustomAuthenticationProvider.authenticate");

        return new AuthenticationTokenUser(username, credentials, userDetails, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
