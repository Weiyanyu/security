package top.yeonon.security.bowser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import top.yeonon.security.bowser.logout.YeononLogoutSuccessHandler;
import top.yeonon.security.bowser.session.YeononInvalidSessionStrategy;
import top.yeonon.security.bowser.session.YeononSessionExpireStrategy;
import top.yeonon.security.core.properties.SecurityProperties;

/**
 * @Author yeonon
 * @date 2018/3/1 0001 19:41
 **/
@Configuration
public class BrowserBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy yeononInvalidSessionStrategy() {
        String invalidUrl = securityProperties.getBrowser().getSession().getSessionInvalidUrl();
        return new YeononInvalidSessionStrategy(invalidUrl);
    }

    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy yeononSessionExpireStrategy() {
        String invalidUrl = securityProperties.getBrowser().getSession().getSessionInvalidUrl();
        return new YeononSessionExpireStrategy(invalidUrl);
    }

    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public LogoutSuccessHandler yeononLogoutSuccessHandler() {
        return new YeononLogoutSuccessHandler(securityProperties.getBrowser().getSignOutUrl());
    }

}
