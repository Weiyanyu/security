package top.yeonon.security.core.validate.code.image;

import lombok.Getter;
import lombok.Setter;
import top.yeonon.security.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Author yeonon
 * @date 2018/2/22 0022 15:15
 **/
@Getter
@Setter
public class ImageCode extends ValidateCode {
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }
}
