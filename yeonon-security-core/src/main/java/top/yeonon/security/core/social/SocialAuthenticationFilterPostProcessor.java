package top.yeonon.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * @Author yeonon
 * @date 2018/3/3 0003 14:46
 **/
public interface SocialAuthenticationFilterPostProcessor {

    void process(SocialAuthenticationFilter authenticationFilter);
}
