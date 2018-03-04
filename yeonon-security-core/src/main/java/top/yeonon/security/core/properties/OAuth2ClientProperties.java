package top.yeonon.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author yeonon
 * @date 2018/3/3 0003 19:25
 **/
@Getter
@Setter
public class OAuth2ClientProperties {

    private String clientId;

    private String clientSecret;

    private int accessTokenValiditySeconds = 7200;

}
