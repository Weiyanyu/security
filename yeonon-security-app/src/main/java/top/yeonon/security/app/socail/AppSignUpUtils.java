package top.yeonon.security.app.socail;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import top.yeonon.security.app.AppSecurityException;

import java.util.concurrent.TimeUnit;

/**
 * @Author yeonon
 * @date 2018/3/3 0003 15:39
 **/
@Component
public class AppSignUpUtils {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    public void saveConnection(WebRequest request, ConnectionData connectionData) {
        redisTemplate.opsForValue().set(getKey(request), connectionData, 10, TimeUnit.MINUTES);
    }

    public void doPostSignUp(String userId, WebRequest request) {
        String key = getKey(request);
        if (!redisTemplate.hasKey(key)) {
            throw new AppSecurityException("没有该用户的信息");
        }
        ConnectionData connectionData = (ConnectionData) redisTemplate.opsForValue().get(key);

        Connection<?> connection = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId())
                .createConnection(connectionData);

        usersConnectionRepository.createConnectionRepository(userId).addConnection(connection);

        redisTemplate.delete(key);
    }

    private String getKey(WebRequest request) {
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isEmpty(deviceId)) {
            throw new AppSecurityException("请在请求头中添加设备ID");
        }
        return "yeonon:security:social.deviceId." + deviceId;
    }

}
