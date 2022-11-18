package comp4342.android.polyyou.activity;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.biz.UserBiz;
import comp4342.android.polyyou.model.User;
import comp4342.android.polyyou.net.CommonCallBack;
import comp4342.android.polyyou.utils.Str;
import comp4342.android.polyyou.utils.T;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;

public class Login extends BaseActivity {

        private Button btnLoginBack;
        private EditText etEmail;
        private EditText etPassword;
        private Button btnConfirmLogin;
        private UserBiz userBiz = new UserBiz();

//        @Override
//        protected void onResume() {
//                super.onResume();
//                CookieJarImpl cookieJar = (CookieJarImpl) OkHttpUtils.getInstance().getOkHttpClient().cookieJar();
//                cookieJar.getCookieStore().removeAll();
//        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_login);
                initView();
                initEvent();
        }

        private void initEvent() {
                T.init(Login.this);
                btnLoginBack.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                                backToWelcome();
                        }
                });

                btnConfirmLogin.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View view){
                                String email = etEmail.getText().toString();
                                String password = etPassword.getText().toString();
                                if(!Str.isValidEmail(email)){
                                        T.showToast("Please enter a polyu email address");  return;
                                }
                                else if(password.isEmpty()){
                                        T.showToast("Password cannot be empty");  return;
                                }
                                startLoadingProgress();
                                userBiz.login(email, password, new CommonCallBack<User>(){
                                        @Override
                                        public void onError(Exception e) {
                                                stopLoadingProgress();
                                                Log.d("login activity", e.getMessage());
                                        }

                                        @Override
                                        public void onSuccess(User response){
                                                stopLoadingProgress();
                                                Log.d("login activity", response.toString());
                                                toHome();
                                        }
                                });
                        }
                });
        }

        private void toHome() {
                Intent intent = new Intent(this, Home.class);
                startActivity(intent);
        }

        private void backToWelcome() {
                Intent intent = new Intent(this, Welcome.class);
                startActivity(intent);
        }

        private void initView() {
                btnLoginBack = findViewById(R.id.button_login_back);
                etEmail = findViewById(R.id.edittext_login_email);
                etPassword = findViewById(R.id.edittext_login_password);
                btnConfirmLogin = findViewById(R.id.button_confirm_login);
        }

        @Override
        protected void onDestroy() {
                super.onDestroy();
                userBiz.onDestroy();
        }
}