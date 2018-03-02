package top.yeonon.security.bowser.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @Author yeonon
 * @date 2018/3/1 0001 18:13
 **/
public class YeononSessionExpireStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {
    public YeononSessionExpireStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        onSessionInvalid(event.getRequest(), event.getResponse());
    }

    @Override
    protected boolean isConcurrency() {
        return false;
    }
}
