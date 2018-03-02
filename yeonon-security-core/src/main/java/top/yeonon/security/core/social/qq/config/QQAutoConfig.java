package top.yeonon.security.core.social.qq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import top.yeonon.security.core.properties.QQProperties;
import top.yeonon.security.core.properties.SecurityProperties;
import top.yeonon.security.core.social.qq.connet.QQConnectionFactory;

/**
 * @Author yeonon
 * @date 2018/2/25 0025 17:14
 **/
@Configuration
@ConditionalOnProperty(prefix = "yeonon.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqConfig = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
    }
}
