package top.yeonon.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @Author yeonon
 * @date 2018/2/26 0026 19:02
 **/
@Component
public class DemoConnectionSignUp implements ConnectionSignUp {


    @Override
    public String execute(Connection<?> connection) {

        return connection.getDisplayName();
    }
}
