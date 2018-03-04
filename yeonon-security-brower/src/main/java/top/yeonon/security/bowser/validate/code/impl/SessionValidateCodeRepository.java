package top.yeonon.security.bowser.validate.code.impl;

import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import top.yeonon.security.core.validate.code.ValidateCode;
import top.yeonon.security.core.validate.code.ValidateCodeRepository;
import top.yeonon.security.core.validate.code.ValidateCodeType;

/**
 * @Author yeonon
 * @date 2018/3/2 0002 19:32
 **/
@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository {


    private final static String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void save(ServletWebRequest request, ValidateCode validateCode, ValidateCodeType validateCodeType) {
        sessionStrategy.setAttribute(request, getSessionKey(request, validateCodeType), validateCode);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return (ValidateCode) sessionStrategy.getAttribute(request, getSessionKey(request, validateCodeType));
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType validateCodeType) {
        sessionStrategy.removeAttribute(request, getSessionKey(request, validateCodeType));
    }

    private String getSessionKey(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return SESSION_KEY_PREFIX + validateCodeType.toString().toUpperCase();
    }
}
