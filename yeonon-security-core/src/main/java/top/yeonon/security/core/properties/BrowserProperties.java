package top.yeonon.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author yeonon
 * @date 2018/2/20 0020 18:27
 **/

@Getter
@Setter
public class BrowserProperties {

    private SessionProperties session = new SessionProperties();

    private String signUpUrl = "/signUp.html";

    private String signOutUrl = "";

    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    private LoginType loginType = LoginType.JSON;

    private int rememberMeSeconds = 3600;

}
