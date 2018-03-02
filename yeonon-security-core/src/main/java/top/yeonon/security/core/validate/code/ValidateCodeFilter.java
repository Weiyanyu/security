package top.yeonon.security.core.validate.code;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import top.yeonon.security.core.properties.SecurityConstants;
import top.yeonon.security.core.properties.SecurityProperties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author yeonon
 * @date 2018/2/22 0022 15:30
 **/
@Setter
@Getter
@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean{

    @Autowired
    private AuthenticationFailureHandler yeononAuthenticationFailerHandler;

    @Autowired
    private SecurityProperties securityProperties;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    private Map<String, ValidateCodeType> urlMap = new HashMap<>();



    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
        addUrlToMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeType.IMAGE);


        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
        addUrlToMap(securityProperties.getCode().getSms().getUrl(), ValidateCodeType.SMS);


    }

    private void addUrlToMap(String urls, ValidateCodeType type) {
        String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urls, ",");

        if (configUrls != null) {
            for (String configUrl : configUrls) {
                urlMap.put(configUrl, type);
            }
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ValidateCodeType type = getValidateCodeType(request);
        if (type != null) {
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type)
                        .validate(new ServletWebRequest(request, response));
            } catch (ValidateCodeException e) {
                yeononAuthenticationFailerHandler.onAuthenticationFailure(request, response, e);
                return;
            }

        }

        filterChain.doFilter(request, response);
    }

    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        ValidateCodeType result = null;
        if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
            Set<String> urls = urlMap.keySet();
            for (String url : urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    result = urlMap.get(url);
                    break;
                }
            }
        }
        return result;
    }



}
