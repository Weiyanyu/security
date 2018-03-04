package top.yeonon.security.app.socail.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;
import top.yeonon.security.core.social.SocialAuthenticationFilterPostProcessor;

/**
 * @Author yeonon
 * @date 2018/3/3 0003 14:54
 **/
@Component
public class AppSocialAuthenticationFilterPostProcessor implements SocialAuthenticationFilterPostProcessor {

    @Autowired
    private AuthenticationSuccessHandler yeononAuthenticationSuccessHandler;

    @Override
    public void process(SocialAuthenticationFilter authenticationFilter) {
        authenticationFilter.setAuthenticationSuccessHandler(yeononAuthenticationSuccessHandler);
    }
}
