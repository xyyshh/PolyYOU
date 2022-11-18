package comp4342.android.polyyou.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import comp4342.android.polyyou.R;

public class Welcome extends AppCompatActivity {

    private Button btnLogin;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();
        initEvent();
    }

    private void initEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLoginActivity();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toSignUpActivity();
            }
        });
    }

    private void toLoginActivity() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    private void toSignUpActivity() {
        Intent intent = new Intent(this, SignupImage.class);
        startActivity(intent);
    }

    private void initView() {
        btnLogin = findViewById(R.id.button_login);
        btnSignUp = findViewById(R.id.button_sign_up);
    }
}