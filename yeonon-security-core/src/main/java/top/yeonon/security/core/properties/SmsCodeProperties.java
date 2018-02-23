package top.yeonon.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author yeonon
 * @date 2018/2/22 0022 16:05
 **/

@Getter
@Setter
public class SmsCodeProperties {
    private int length = 6;
    private int expireIn = 60;

    String url;
}
