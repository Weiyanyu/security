package top.yeonon.security.app.socail.openid;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import top.yeonon.security.core.authentication.mobile.SmsCodeAuthenticationToken;
import top.yeonon.security.core.properties.SecurityConstants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author yeonon
 * @date 2018/3/3 0003 10:57
 **/
public class OpenIdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


    private final static String openIdParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_OPENID;

    private final static String providerIdParameter = SecurityConstants.DEFAULT_PARAMETER_NAME_PROVIDERID;


    private boolean postOnly = true;

    public OpenIdAuthenticationFilter() {
        super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_OPENID, "POST"));
    }




    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String openId = obtainOpenId(request);
        String providerId = obtainProviderId(request);

        if (openId == null)
            openId = "";
        if (providerId == null)
            providerId = "";

        openId.trim();
        providerId.trim();


        OpenIdAuthenticationToken authRequest = new OpenIdAuthenticationToken(openId, providerId);
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);

    }

    protected void setDetails(HttpServletRequest request,
                              OpenIdAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    protected String obtainOpenId(HttpServletRequest request) {
        return request.getParameter(openIdParameter);
    }

    protected String obtainProviderId(HttpServletRequest request) {
        return request.getParameter(providerIdParameter);
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public static String getOpenIdParameter() {
        return openIdParameter;
    }

    public static String getProviderIdParameter() {
        return providerIdParameter;
    }
}
