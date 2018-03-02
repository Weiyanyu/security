package top.yeonon.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author yeonon
 * @date 2018/2/25 0025 17:12
 **/
@Setter
@Getter
public class SocialProperties {
    private QQProperties qq = new QQProperties();

    private WeixinProperties weixin = new WeixinProperties();

    private String filterProcessesUrl = "/auth";
}
