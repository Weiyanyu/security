package top.yeonon.security.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @Author yeonon
 * @date 2018/3/1 0001 15:27
 **/
@Setter
@Getter
public class WeixinProperties extends SocialProperties {

    private String providerId = "weixin";

}
