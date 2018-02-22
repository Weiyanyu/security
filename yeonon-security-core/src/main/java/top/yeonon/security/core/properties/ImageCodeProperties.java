package top.yeonon.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author yeonon
 * @date 2018/2/22 0022 16:05
 **/

@Getter
@Setter
public class ImageCodeProperties {
    private int width = 67;
    private int height = 23;
    private int length = 4;
    private int expireIn = 60;

    String url;
}
