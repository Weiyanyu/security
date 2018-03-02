package top.yeonon.security.core.social.weixin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import top.yeonon.security.core.properties.SecurityProperties;
import top.yeonon.security.core.properties.WeixinProperties;
import top.yeonon.security.core.social.YeononConnectionView;
import top.yeonon.security.core.social.weixin.connet.WeixinConnectionFactory;

/**
 * @Author yeonon
 * @date 2018/3/1 0001 15:26
 **/
@Configuration
@ConditionalOnProperty(prefix = "yeonon.security.social.weixin", name = "app-id")
public class WeixinAutoConfiguration extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        WeixinProperties weixinConfig = securityProperties.getSocial().getWeixin();

        return new WeixinConnectionFactory(weixinConfig.getProviderId(), weixinConfig.getAppId(), weixinConfig.getAppSecret());
    }


    @Bean("connect/weixinConnected")
    @ConditionalOnMissingBean(name = "connect/weixinConnected")
    public YeononConnectionView yeononConnectionView() {
        return new YeononConnectionView();
    }
}
