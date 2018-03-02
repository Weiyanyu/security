package top.yeonon.validate;

import org.springframework.web.context.request.ServletWebRequest;
import top.yeonon.security.core.validate.code.image.ImageCode;
import top.yeonon.security.core.validate.code.ValidateCodeGenerator;

/**
 * @Author yeonon
 * @date 2018/2/22 0022 16:41
 **/
//@Component("imageValidateCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator{

    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println("更高级的图形验证码");
        return null;
    }
}
