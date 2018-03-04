package top.yeonon.security.core.properties;

/**
 * @Author yeonon
 * @date 2018/2/24 0024 16:19
 **/
public interface SecurityConstants {

    /**
     * 默认的处理验证码URL前缀
     */
    String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";

    /**
     * 需要跳转的URL
     */
    String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";

    /**
     * 表单登录的请求URL
     */
    String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";

    /**
     * 短信登录请求URL
     */
    String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";

    /**
     * openId方式请求URL
     */
    String DEFAULT_LOGIN_PROCESSING_URL_OPENID = "/authentication/openId";

    /**
     * 默认登录界面
     */
    String DEFAULT_LOGIN_PAGE_URL = "/signIn.html";

    /**
     * 图片验证码HTTP请求中携带参数名字
     */
    String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";

    /**
     * 短信验证码HTTP请求中携带参数名字
     */
    String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";

    /**
     * openId参数名字
     */

    String DEFAULT_PARAMETER_NAME_OPENID = "openId";

    /**
     * providerId参数名字
     */

    String DEFAULT_PARAMETER_NAME_PROVIDERID = "providerId";

    /**
     * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";

    String DEFAULT_SESSION_INVALID_URL = "/session-invalid.html";

}
