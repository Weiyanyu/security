package top.yeonon.security.app.validate.code.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import top.yeonon.security.core.properties.SecurityProperties;
import top.yeonon.security.core.validate.code.ValidateCode;
import top.yeonon.security.core.validate.code.ValidateCodeException;
import top.yeonon.security.core.validate.code.ValidateCodeRepository;
import top.yeonon.security.core.validate.code.ValidateCodeType;

import java.util.concurrent.TimeUnit;

/**
 * @Author yeonon
 * @date 2018/3/2 0002 19:27
 **/
@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;


    @Override
    public void save(ServletWebRequest request, ValidateCode validateCode, ValidateCodeType validateCodeType) {
        redisTemplate.opsForValue().set(buildKey(request, validateCodeType), validateCode, securityProperties.getCode().getSms().getExpireIn(), TimeUnit.SECONDS);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return (ValidateCode) redisTemplate.opsForValue().get(buildKey(request, validateCodeType));
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType validateCodeType) {
        redisTemplate.delete(buildKey(request, validateCodeType));
    }

    private String buildKey(ServletWebRequest request, ValidateCodeType validateCodeType) {
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)) {
            throw new ValidateCodeException("请在请求头中带上deviceId");
        }

        return "code:" + validateCodeType.toString().toLowerCase() + ":" + deviceId;
    }
}
