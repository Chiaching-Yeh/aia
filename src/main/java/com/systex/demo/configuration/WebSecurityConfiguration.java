package manage.config;

import com.systex.support.FunctionLogSupport;
import lombok.extern.slf4j.Slf4j;
import manage.security.CustomAuthenticationProvider;
import manage.security.CustomWebAuthenticationDetailsSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

	@Value("${app.upload.path}")
    private String ROOTPATH_AP;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebAuthenticationDetailsSource customWebAuthenticationDetailsSource() {
        return new CustomWebAuthenticationDetailsSource();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomAuthenticationProvider();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
                "/static/**",
                "/favicon.ico",
                "/verifyCode",
                ROOTPATH_AP + "**",
                "/*audit",
                "/logouts",
                "/common",
                "/syncdata/**",
                "/loginSSO",
                "/fckdowndoc**",
                "/uploaddowndoc**",
                "/uploadviewdoc**",
                "/videodowndoc**",
                "/websitedowndoc**",
                "/MeetingDataQuery**",
                "/MeetingDataUpdate**",
                "/MeetingDataDelete**",
                "/check.jsp",
                "/app/auditunit/Sync",
                "/app/zipschedule/sysview",
                "/system/synceipuser",
                "/forgetPassword",
                "/userchangePassword",
                "/app/important/important_open",
                "/app/auditconfig/auditgroup_mailcron",
                "/app/msChangemy/test",
                "/api/egov/test",
                "/OIDC",
                "/OIDClogin",
                "/OIDCcallback",
                "/OIDClogout",
                "/OIDCsync",
                "/indextryOIDC"
                );
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
    	FunctionLogSupport.start("WebSecurityConfiguration.configure");

        http.sessionManagement(sess -> sess
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .invalidSessionUrl("/logouts")
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .enableSessionUrlRewriting(false)
            .sessionFixation()
            .migrateSession()
        );

        http.authorizeHttpRequests((authz) -> authz
            .requestMatchers("/login", "/CheckApService")
            .permitAll()
            .anyRequest().authenticated()
        );

        http.formLogin(formLogin -> formLogin
            .loginPage("/index")
            .loginProcessingUrl("/index")
            .failureUrl("/index?error=true")
            .authenticationDetailsSource(customWebAuthenticationDetailsSource())
            .defaultSuccessUrl("/", true)
            .permitAll()
        );

        http.logout(logout -> logout
            .logoutSuccessUrl("/logouts")
            .invalidateHttpSession(true)
            .permitAll()
        );

        http.headers(headers -> headers
            .xssProtection(
                xss -> xss.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK)
            )
/*
            .contentSecurityPolicy(
                cps -> cps.policyDirectives(
                    "default-src 'self';" +
                    "base-uri 'self';" +
                    "font-src 'self' https: data:;" +
                    "form-action 'self';" +
                    "frame-ancestors 'self';" +
                    "img-src 'self' data:;" +
                    "object-src 'none';" +
                    "script-src 'self' 'nonce-random_nonce';" +
                    "script-src-attr 'none';" +
                    "style-src 'self' https: 'unsafe-inline';" +
                    "upgrade-insecure-requests"
                )
            )
*/
            .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
        );

        http.authenticationProvider(authenticationProvider());

        http.csrf(AbstractHttpConfigurer::disable);

        FunctionLogSupport.end("WebSecurityConfiguration.configure");

        return http.build();

    }

}
