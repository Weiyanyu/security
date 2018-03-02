package top.yeonon.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @Author yeonon
 * @date 2018/2/26 0026 15:11
 **/
public class YeononSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;

    public YeononSpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) object;
        filter.setFilterProcessesUrl(filterProcessesUrl);
        return (T) filter;
    }
}
