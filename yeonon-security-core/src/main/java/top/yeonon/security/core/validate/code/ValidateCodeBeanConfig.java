package top.yeonon.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.yeonon.security.core.properties.SecurityProperties;
import top.yeonon.security.core.validate.code.image.ImageValidateCodeGenerator;
import top.yeonon.security.core.validate.code.sms.DefaultSmsCodeSender;
import top.yeonon.security.core.validate.code.sms.SmsCodeSender;

/**
 * @Author yeonon
 * @date 2018/2/22 0022 16:36
 **/
@Configuration
public class ValidateCodeBeanConfig {


    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        return new ImageValidateCodeGenerator();
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }
}
