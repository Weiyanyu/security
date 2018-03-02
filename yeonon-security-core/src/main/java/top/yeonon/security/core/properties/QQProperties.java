package top.yeonon.security.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @Author yeonon
 * @date 2018/2/25 0025 17:11
 **/
@Setter
@Getter
public class QQProperties extends SocialProperties {

    private String providerId = "qq";



}
