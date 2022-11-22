package comp4342.android.polyyou.biz;

import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;

import comp4342.android.polyyou.Config;
import comp4342.android.polyyou.model.Data;
import comp4342.android.polyyou.model.User;
import comp4342.android.polyyou.net.CommonCallBack;

public class UserBiz {

    public void register(String name, String email, String password, String verificationCode, CommonCallBack<User> commonCallBack) {
        OkHttpUtils
                .post()
                .url(Config.baseUrl + "login-center/register/")
                .tag(this)
                .addParams("nickname", name)
                .addParams("email", email)
                .addParams("pwd", password)
                .addParams("profileImage", "")
                .addParams("verificationCode", verificationCode)
                .build()
                .execute(commonCallBack);
    }

    public void updateProfileImage(String email, File file, CommonCallBack<User> commonCallBack) {
        OkHttpUtils
                .post()
                .url(Config.baseUrl + "login-center/register-image")
                .addParams("email", email)
                .addFile("image", file.getName(), file)
                .build()
                .execute(commonCallBack);
    }



    public void sendVerificationCode(String email, CommonCallBack<Data> commonCallBack) {
        OkHttpUtils
                .post()
                .url(Config.baseUrl + "logic-center/email-verify/")
                .tag(this)
                .addParams("to", email)
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

    public void getUserById(String id, CommonCallBack<User> commonCallBack) {
        OkHttpUtils
                .get()
                .url(Config.baseUrl + "login-center/show-by-id")
                .tag(this)
                .addParams("id", id)
                .build()
                .execute(commonCallBack);
    }
    public void requestVerificationCode(String email, CommonCallBack<Data> commonCallBack) {
        OkHttpUtils
                .get()
                .url(Config.baseUrl + "login-center/email-verify")
                .tag(this)
                .addParams("to", email)
                .build()
                .execute(commonCallBack);
    }

    public void onDestroy() {
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
