package top.yeonon.security.core.social.weixin.connet;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import top.yeonon.security.core.social.weixin.api.Weixin;
import top.yeonon.security.core.social.weixin.api.WeixinImpl;

/**
 * @Author yeonon
 * @date 2018/3/1 0001 15:11
 **/
public class WeixinServiceProvider extends AbstractOAuth2ServiceProvider<Weixin> {
    /**
     * 微信获取授权码的url
     */
    private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";
    /**
     * 微信获取accessToken的url
     */
    private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";


    public WeixinServiceProvider(String appId, String appSecret) {
        super(new WeixinOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
    }


    @Override
    public Weixin getApi(String accessToken) {
        return new WeixinImpl(accessToken);
    }
}
