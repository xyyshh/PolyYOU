package comp4342.android.polyyou.biz;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.OkHttpRequest;

import comp4342.android.polyyou.Config;
import comp4342.android.polyyou.model.User;
import comp4342.android.polyyou.net.CommonCallBack;

public class UserBiz {
    public void login(String email, String password, CommonCallBack<User> commonCallBack) {
        OkHttpUtils
                .get()
                .url(Config.baseUrl + "login-center/login/")
                .tag(this)
                .addParams("email", email)
                .addParams("pwd", password)
                .build()
                .execute(commonCallBack);
    }

    public void onDestroy() {
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
