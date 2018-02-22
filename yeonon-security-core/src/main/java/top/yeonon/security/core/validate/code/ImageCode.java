package top.yeonon.security.core.validate.code;

import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Author yeonon
 * @date 2018/2/22 0022 15:15
 **/
@Getter
@Setter
public class ImageCode {
    private BufferedImage image;

    private String code;

    private LocalDateTime expireTime;


    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }

    public ImageCode(BufferedImage image, String code, int expireIn) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpire() {
        return LocalDateTime.now().isAfter(expireTime);
    }


}
