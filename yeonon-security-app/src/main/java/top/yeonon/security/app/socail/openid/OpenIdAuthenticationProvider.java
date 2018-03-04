package top.yeonon.security.app.socail.openid;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.provider.SocialAuthenticationService;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author yeonon
 * @date 2018/3/3 0003 11:10
 **/
@Getter
@Setter
public class OpenIdAuthenticationProvider implements AuthenticationProvider {

    private SocialUserDetailsService userDetailsService;

    private UsersConnectionRepository usersConnectionRepository;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        OpenIdAuthenticationToken authenticationToken = (OpenIdAuthenticationToken) authentication;

        Set<String> providerIds = new HashSet<>();
        providerIds.add((String) authenticationToken.getPrincipal());

        Set<String> userIds = usersConnectionRepository.findUserIdsConnectedTo(authenticationToken.getProviderId(), providerIds);

        if (CollectionUtils.isEmpty(userIds) || userIds.size() != 1) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        String userId = userIds.iterator().next();

        UserDetails userDetails = userDetailsService.loadUserByUserId(userId);

        if (userDetails == null) {
            throw new InternalAuthenticationServiceException("获取用户信息失败");
        }

        OpenIdAuthenticationToken authResult = new OpenIdAuthenticationToken(userDetails, userDetails.getAuthorities());

        authResult.setDetails(authenticationToken.getDetails());

        return authResult;


    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OpenIdAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
