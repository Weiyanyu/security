package top.yeonon.security.core.validate.code;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

/**
 * @Author yeonon
 * @date 2018/2/23 0023 16:10
 **/
public interface ValidateCodeProcessor {
    void create(ServletWebRequest request) throws IOException, ServletRequestBindingException;

    void validate(ServletWebRequest request);
}
