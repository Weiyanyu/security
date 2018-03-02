package top.yeonon.security.core.social.weixin.api;

/**
 * @Author yeonon
 * @date 2018/3/1 0001 14:32
 **/
public interface Weixin {
    WeixinUserInfo getUserInfo(String openId);
}
