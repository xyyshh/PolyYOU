package comp4342.android.polyyou.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.utils.Str;
import comp4342.android.polyyou.utils.T;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Login extends AppCompatActivity {

        private Button btnLoginBack;
        private EditText etEmail;
        private EditText etPassword;
        private Button btnConfirmLogin;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_login);
                initView();
                initEvent();
        }

        private void initEvent() {
                btnLoginBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                backToWelcome();
                        }
                });

                btnConfirmLogin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                String email = etEmail.getText().toString();
                                String password = etPassword.getText().toString();
                                if(!Str.isValidEmail(email)) {
                                        T.showToast(Login.this, "Please enter a polyu email address");  return;
                                }
                                else if(password.isEmpty()) {
                                        T.showToast(Login.this, "Password cannot be empty");  return;
                                }
                        }
                });
        }

        private void backToWelcome() {
                Intent intent = new Intent(this, Welcome.class);
                startActivity(intent);
        }

        private void initView() {
                btnLoginBack = findViewById(R.id.button_login_back);
                etEmail = findViewById(R.id.edittext_login_email);
                etPassword = findViewById(R.id.edittext_login_password);
                btnConfirmLogin = findViewById(R.id.button_login);
        }
}