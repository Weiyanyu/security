package top.yeonon.security.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import top.yeonon.security.app.socail.AppSignUpUtils;
import top.yeonon.security.core.support.SocialUserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author yeonon
 * @date 2018/3/3 0003 16:12
 **/
@RestController
public class AppSecurityController {


    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @Autowired
    private AppSignUpUtils appSignUpUtils;


    @GetMapping("/social/signUp")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
        SocialUserInfo socialUserInfo = new SocialUserInfo();
        Connection connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        socialUserInfo.setProviderId(connection.getKey().getProviderId());
        socialUserInfo.setProviderUserId(connection.getKey().getProviderUserId());
        socialUserInfo.setNickName(connection.getDisplayName());
        socialUserInfo.setHeadimg(connection.getImageUrl());

        appSignUpUtils.saveConnection(new ServletWebRequest(request), connection.createData());


        return socialUserInfo;
    }
}
