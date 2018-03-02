package top.yeonon.security.bowser.support;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author yeonon
 * @date 2018/2/26 0026 18:11
 **/
@Getter
@Setter
public class SocialUserInfo {
    private String providerId;

    private String providerUserId;

    private String nickName;

    private String headimg;
}
