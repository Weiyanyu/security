package top.yeonon.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author yeonon
 * @date 2018/2/22 0022 16:28
 **/
public interface ValidateCodeGenerator {
    ValidateCode generate(ServletWebRequest request);
}
