package top.yeonon.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.yeonon.security.core.properties.SecurityProperties;

/**
 * @Author yeonon
 * @date 2018/2/22 0022 16:36
 **/
@Configuration
public class ValidateCodeBeanConfig {

    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageValidateCodeGenerator imageValidateCodeGenerator = new ImageValidateCodeGenerator();
        imageValidateCodeGenerator.setSecurityProperties(securityProperties);
        return imageValidateCodeGenerator;
    }
}
