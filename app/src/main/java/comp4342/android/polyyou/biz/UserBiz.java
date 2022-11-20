package comp4342.android.polyyou.biz;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.request.OkHttpRequest;

import comp4342.android.polyyou.Config;
import comp4342.android.polyyou.model.User;
import comp4342.android.polyyou.net.CommonCallBack;

public class UserBiz {

    public void register(String name, String email, String password, String image, CommonCallBack<User> commonCallBack) {
        OkHttpUtils
                .post()
                .url(Config.baseUrl + "login-center/register/")
                .tag(this)
                .addParams("nickname", name)
                .addParams("email", email)
                .addParams("pwd", password)
                .addParams("profileImage", image)
                .build()
                .execute(commonCallBack);
    }

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
