package comp4342.android.polyyou;

import comp4342.android.polyyou.model.User;
import comp4342.android.polyyou.utils.SPUtils;

public class UserInfoHolder {
    private static UserInfoHolder eInstance = new UserInfoHolder();
    private User eUser;
    private final String KEY_ID = "key_id";
    public static UserInfoHolder getInstance() {
        return eInstance;
    }
    public void setUser(User user) {
        eUser = user;
        if(user != null) {
           // SPUtils.getInstance().put(KEY_ID, user.getId());
        }
    }

    public User getUser() {
        User user = eUser;
        if(user != null) {
            return user;
        }
        return null;
    }
}
