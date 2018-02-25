package top.yeonon.security.core.social.qq.connet;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import top.yeonon.security.core.social.qq.api.QQ;
import top.yeonon.security.core.social.qq.api.QQUserInfo;

/**
 * @Author yeonon
 * @date 2018/2/25 0025 16:40
 **/
public class QQAdapter implements ApiAdapter<QQ> {
    @Override
    public boolean test(QQ api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = api.getUserInfo();
        //适配connectionValues 标准数据结构
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl());
        values.setProfileUrl(null);
        values.setProviderUserId(userInfo.getOpenId());

    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {
        //do nothing
    }
}
