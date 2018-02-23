package top.yeonon.security.core.validate.code.sms;

/**
 * @Author yeonon
 * @date 2018/2/23 0023 15:23
 **/
public interface SmsCodeSender {
    void send(String mobile, String code);
}
