package top.yeonon.security.core.validate.code.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import top.yeonon.security.core.validate.code.*;

import java.io.IOException;
import java.util.Map;

/**
 * @Author yeonon
 * @date 2018/2/23 0023 16:13
 **/
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

    @Autowired
    private ValidateCodeRepository validateCodeRepository;

    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGenerators;

    @Override
    public void create(ServletWebRequest request) throws IOException, ServletRequestBindingException {
        C validateCode = generate(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    @Override
    public void validate(ServletWebRequest request) {
        ValidateCodeType type = getValidateCodeType();

        C codeInSession = (C) validateCodeRepository.get(request, type);

        String codeInRequest;
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),
                    type.getParamNameOnValidate());
        } catch (ServletRequestBindingException e) {
            throw new ValidateCodeException("验证码获取失败");
        }

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException(type + "验证码的值不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException(type + "验证码不存在");
        }

        if (codeInSession.isExpire()) {
            validateCodeRepository.remove(request, type);
            throw new ValidateCodeException(type + "验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException(type + "验证码不匹配");
        }

        validateCodeRepository.remove(request, type);


    }

    private ValidateCodeType getValidateCodeType() {
        String type = StringUtils.substringBefore(getClass().getSimpleName(), "ValidateCodeProcessor");
        return ValidateCodeType.valueOf(type.toUpperCase());
    }

    @SuppressWarnings("unchecked")
    private C generate(ServletWebRequest request) {
        String type = getProcessorType(request);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(type + "ValidateCodeGenerator");
        return (C) validateCodeGenerator.generate(request);
    }

    private void save(ServletWebRequest request, C validateCode) {
        ValidateCode code = new ValidateCode(validateCode.getCode(), validateCode.getExpireTime());
        validateCodeRepository.save(request, code, getValidateCodeType());
    }

    protected abstract void send(ServletWebRequest request, C validateCode) throws ServletRequestBindingException, IOException;

    private String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
    }
}
