package top.yeonon.security.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;
import top.yeonon.security.app.socail.openid.OpenIdAuthenticationSecurityConfig;
import top.yeonon.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import top.yeonon.security.core.properties.SecurityConstants;
import top.yeonon.security.core.properties.SecurityProperties;
import top.yeonon.security.core.validate.code.ValidateCodeSecurityConfig;

/**
 * @Author yeonon
 * @date 2018/3/2 0002 13:57
 **/
@Configuration
@EnableResourceServer
public class YeononResourceServer extends ResourceServerConfigurerAdapter {

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private AuthenticationSuccessHandler yeononAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler yeononAuthenticationFailerHandler;

    @Autowired
    private SpringSocialConfigurer yeononSocialSecurityConfig;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(yeononAuthenticationSuccessHandler)
                .failureHandler(yeononAuthenticationFailerHandler);

        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(yeononSocialSecurityConfig)
                .and()
                .apply(openIdAuthenticationSecurityConfig)
                .and()
                .authorizeRequests()
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_OPENID,
                        securityProperties.getBrowser().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                        securityProperties.getBrowser().getSignUpUrl(),
                        SecurityConstants.DEFAULT_SESSION_INVALID_URL,
                        securityProperties.getBrowser().getSignOutUrl(),
                        "/users/regist", "/social/signUp")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
