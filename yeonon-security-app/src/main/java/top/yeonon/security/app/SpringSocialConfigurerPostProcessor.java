package top.yeonon.security.app;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import top.yeonon.security.core.social.YeononSpringSocialConfigurer;

/**
 * @Author yeonon
 * @date 2018/3/3 0003 16:07
 **/
@Component
public class SpringSocialConfigurerPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (StringUtils.equals(beanName, "yeononSocialSecurityConfig")) {
            YeononSpringSocialConfigurer yeononSpringSocialConfigurer = (YeononSpringSocialConfigurer) bean;
            yeononSpringSocialConfigurer.signupUrl("/social/signUp");
            return yeononSpringSocialConfigurer;
        } else {
            return bean;
        }
    }
}
