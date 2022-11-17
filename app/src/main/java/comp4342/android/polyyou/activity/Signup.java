package comp4342.android.polyyou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.utils.Str;
import comp4342.android.polyyou.utils.T;

public class Signup extends AppCompatActivity {

    private Button btnSignBack;
    private EditText etName;
    private EditText etEmail;
    private EditText etPassword1;
    private EditText etPassword2;
    private Button btnNext;


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
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etName.getText().toString();
                String password1 = etPassword1.getText().toString();
                String password2 = etPassword2.getText().toString();
                String email = etEmail.getText().toString();
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
                else if(!password1.equals(password2)) {
                    T.showToast( "Please enter the same passwords");  return;
                }
                else if(!Str.isValidPassword(password1)) {
                    T.showToast( "Please enter valid password");
                }
            }
        });
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
    }
}