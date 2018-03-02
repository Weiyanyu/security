package top.yeonon.security.core.social.weixin.connet;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;
import top.yeonon.security.core.social.weixin.api.Weixin;

/**
 * @Author yeonon
 * @date 2018/3/1 0001 15:18
 **/
public class WeixinConnectionFactory extends OAuth2ConnectionFactory<Weixin> {

    public WeixinConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new WeixinServiceProvider(appId, appSecret), new WeixinAdapter());
    }

    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {
        if (accessGrant instanceof WeixinAccessGrant) {
            return ((WeixinAccessGrant) accessGrant).getOpenId();
        }
        return null;
    }

    @Override
    public Connection<Weixin> createConnection(AccessGrant accessGrant) {
        return new OAuth2Connection<Weixin>(getProviderId(), extractProviderUserId(accessGrant), accessGrant.getAccessToken(),
                accessGrant.getRefreshToken(), accessGrant.getExpireTime(), getOAuth2ServiceProvider(), getApiAdapter(extractProviderUserId(accessGrant)));
    }

    @Override
    public Connection<Weixin> createConnection(ConnectionData data) {
        return new OAuth2Connection<Weixin>(data, getOAuth2ServiceProvider(), getApiAdapter(data.getProviderUserId()));
    }

    private ApiAdapter<Weixin> getApiAdapter(String providerUserId) {
        return new WeixinAdapter(providerUserId);
    }

    private OAuth2ServiceProvider<Weixin> getOAuth2ServiceProvider() {
        return (OAuth2ServiceProvider<Weixin>) getServiceProvider();
    }
}
