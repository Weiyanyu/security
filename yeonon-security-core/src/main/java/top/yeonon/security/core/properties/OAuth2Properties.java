package top.yeonon.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author yeonon
 * @date 2018/3/3 0003 19:25
 **/
@Setter
@Getter
public class OAuth2Properties {

    private String jwtSigningKey = "yeonon";

    private OAuth2ClientProperties[] clients = {};
}
