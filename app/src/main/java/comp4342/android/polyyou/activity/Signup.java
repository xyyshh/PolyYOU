package comp4342.android.polyyou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.biz.UserBiz;
import comp4342.android.polyyou.model.CurrentUser;
import comp4342.android.polyyou.model.Data;
import comp4342.android.polyyou.model.Response;
import comp4342.android.polyyou.model.User;
import comp4342.android.polyyou.net.CommonCallBack;
import comp4342.android.polyyou.utils.Str;
import comp4342.android.polyyou.utils.T;
import comp4342.android.polyyou.utils.CountDownUtils;

public class Signup extends BaseActivity {

    private Button btnSignBack;
    private EditText etName;
    private EditText etEmail;
    private EditText etPassword1;
    private EditText etPassword2;
    private Button btnNext;
    private Button btnVeri;
    private EditText verification;

    private UserBiz userBiz = new UserBiz();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initView();
        initEvent();
    }

    private void initEvent() {
        btnSignBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToWelcome();
            }
        });

        if(Str.isValidEmail(etEmail.getText().toString())){

        }
        btnVeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Str.isValidEmail(etEmail.getText().toString())){
                    userBiz.requestVerificationCode(etEmail.getText().toString(), new CommonCallBack<Data>() {
                        @Override
                        public void onError(Exception e) {
                            stopLoadingProgress();
                            Log.d("sign up activity", e.getMessage());
                        }

                        @Override
                        public void onSuccess(Data response) {
                            stopLoadingProgress();
                            Log.d("sign_up_user", "success");
                        }
                    });
                    new CountDownUtils(
                            60 * 1000,
                            1000,
                            btnVeri)
                            .start();
                }else{
                    T.showToast( "You should input a valid PolyU email first!"); return;
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etName.getText().toString();
                String password1 = etPassword1.getText().toString();
                String password2 = etPassword2.getText().toString();
                String email = etEmail.getText().toString();
                String code = verification.getText().toString();
                if(TextUtils.isEmpty(username)) {
                    T.showToast( "User Name cannot be empty"); return;
                }
                else if(TextUtils.isEmpty(email)) {
                    T.showToast( "Email Address cannot be empty");  return;
                }
                else if(!Str.isValidEmail(email)) {
                    T.showToast( "Please enter a polyu email address");  return;
                }
                else if(TextUtils.isEmpty(password1) || TextUtils.isEmpty(password2)) {
                    T.showToast( "Password cannot be empty");  return;
                }
                else if (TextUtils.isEmpty(code)) {
                    T.showToast("Please enter the verification code"); return;
                }
                else if(!password1.equals(password2)) {
                    T.showToast( "Please enter the same passwords");  return;
                }
                else if(!Str.isValidPassword(password1)) {
                    T.showToast( "Please enter valid password");
                }
                startLoadingProgress();
                userBiz.register(username, email, password1, code, new CommonCallBack<User>(){
                    @Override
                    public void onError(Exception e) {
                        stopLoadingProgress();
                        Log.d("login activity", e.getMessage());
                    }

                    @Override
                    public void onSuccess(User response){
                        stopLoadingProgress();
                        Log.d("register_activity", response.toString());
                        saveUser(response);
                        CurrentUser.setUser(response);
                        toProfileImage();
                    }
                });
            }
        });

    }

    private void toProfileImage() {
        Intent intent = new Intent(this, ImageTest.class);
        startActivity(intent);
    }

    private void backToWelcome() {
        Intent intent = new Intent(this, Welcome.class);
        startActivity(intent);
    }

    private void initView() {
        btnSignBack = findViewById(R.id.button_sign_back);
        etName = findViewById(R.id.edittext_sign_name);
        etEmail = findViewById(R.id.edittext_sign_email);
        etPassword1 = findViewById(R.id.edittext_sign_password1);
        etPassword2 = findViewById(R.id.edittext_sign_password2);
        btnNext = findViewById(R.id.button_signup_1);
        btnVeri = findViewById(R.id.send_verification);
        verification = findViewById(R.id.edittext_verification);
    }
}