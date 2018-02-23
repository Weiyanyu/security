package top.yeonon.security.core.validate.code.image;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;
import top.yeonon.security.core.validate.code.ValidateCode;
import top.yeonon.security.core.validate.code.impl.AbstractValidateCodeProcessor;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * @Author yeonon
 * @date 2018/2/23 0023 16:34
 **/
@Component
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws ServletRequestBindingException, IOException {
        ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }
}
