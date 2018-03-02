package top.yeonon.security.core.validate.code.sms;

/**
 * @Author yeonon
 * @date 2018/2/23 0023 15:24
 **/
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机" + mobile + "发送短信验证码 ： " + code);
    }
}
