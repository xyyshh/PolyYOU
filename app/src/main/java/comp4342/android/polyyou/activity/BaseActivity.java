package comp4342.android.polyyou.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.biz.UserBiz;
import comp4342.android.polyyou.utils.Str;
import comp4342.android.polyyou.utils.T;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("loading...");
    }

    protected void startLoadingProgress() {
        progressDialog.show();
    }

    protected void stopLoadingProgress() {
        if(progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }
}