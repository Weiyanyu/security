package top.yeonon.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author yeonon
 * @date 2018/2/22 0022 15:36
 **/
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
