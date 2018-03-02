package top.yeonon.security.core.social.qq.connet;

import lombok.extern.java.Log;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @Author yeonon
 * @date 2018/2/26 0026 16:05
 **/
@Log
public class QQOAuth2Template extends OAuth2Template {


    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String responseStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
        log.info("获取accessToken的响应 " + responseStr);

        String[] item = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr, "&");

        String accessToken = StringUtils.substringAfter(item[0], "=");
        Long expireIn = new Long(StringUtils.substringAfter(item[1], "="));
        String refreshToken = StringUtils.substringAfter(item[2], "=");

        return new AccessGrant(accessToken, null, refreshToken, expireIn);
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        return restTemplate;
    }
}
