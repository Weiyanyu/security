package top.yeonon.security.core.social.weixin.connet;

import lombok.Getter;
import lombok.Setter;
import org.springframework.social.oauth2.AccessGrant;

/**
 * @Author yeonon
 * @date 2018/3/1 0001 14:41
 **/
@Getter
@Setter
public class WeixinAccessGrant extends AccessGrant {


    private String openId;

    public WeixinAccessGrant() {
        super("");
    }

    public WeixinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }


}
