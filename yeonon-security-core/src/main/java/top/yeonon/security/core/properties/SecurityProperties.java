package top.yeonon.security.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author yeonon
 * @date 2018/2/20 0020 18:26
 **/

@ConfigurationProperties(prefix = "yeonon.security")
@Setter
@Getter
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    private SocialProperties social = new SocialProperties();

}
