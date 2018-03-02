package top.yeonon.security.core.social.weixin.connet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import top.yeonon.security.core.social.weixin.api.Weixin;
import top.yeonon.security.core.social.weixin.api.WeixinUserInfo;

/**
 * @Author yeonon
 * @date 2018/3/1 0001 15:08
 **/
@Setter
@Getter
@NoArgsConstructor
public class WeixinAdapter implements ApiAdapter<Weixin> {


    private String openId;

    public WeixinAdapter(String openId) {
        this.openId = openId;
    }

    @Override
    public boolean test(Weixin api) {
        return true;
    }

    @Override
    public void setConnectionValues(Weixin api, ConnectionValues values) {
        WeixinUserInfo userInfo = api.getUserInfo(openId);
        values.setProviderUserId(userInfo.getOpenid());
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getHeadimgurl());
    }

    @Override
    public UserProfile fetchUserProfile(Weixin api) {
        return null;
    }

    @Override
    public void updateStatus(Weixin api, String message) {
        //do nothing
    }
}
