package top.yeonon.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author yeonon
 * @date 2018/3/1 0001 19:13
 **/
@Getter
@Setter
public class SessionProperties {
    private int maximumSessions = 1;

    private boolean maxSessionsPreventsLogin = false;

    private String sessionInvalidUrl = SecurityConstants.DEFAULT_SESSION_INVALID_URL;


}
