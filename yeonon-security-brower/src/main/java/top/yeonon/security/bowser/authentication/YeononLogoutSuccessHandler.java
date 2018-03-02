package top.yeonon.security.bowser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import top.yeonon.security.core.support.SampleResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author yeonon
 * @date 2018/3/2 0002 12:13
 **/
@Log
public class YeononLogoutSuccessHandler implements LogoutSuccessHandler {

    private String signOutUrl;

    public YeononLogoutSuccessHandler(String signOutUrl) {
        this.signOutUrl = signOutUrl;
    }

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("退出成功");
        if (StringUtils.isBlank(signOutUrl)) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SampleResponse("退出成功")));
        } else {
            response.sendRedirect(signOutUrl);
        }
    }
}
