package top.yeonon.security.core.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import top.yeonon.security.core.properties.SecurityConstants;

/**
 * @Author yeonon
 * @date 2018/2/24 0024 18:55
 **/
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter  {

    @Autowired
    private AuthenticationSuccessHandler yeononAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler yeononAuthenticationFailerHandler;

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(yeononAuthenticationSuccessHandler)
                .failureHandler(yeononAuthenticationFailerHandler);
    }

}
