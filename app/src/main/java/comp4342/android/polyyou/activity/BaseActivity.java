package comp4342.android.polyyou.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.biz.UserBiz;
import comp4342.android.polyyou.model.CurrentUser;
import comp4342.android.polyyou.model.User;
import comp4342.android.polyyou.utils.Str;
import comp4342.android.polyyou.utils.T;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    protected void saveUser(User user) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try{
            out = openFileOutput("user", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            Date dNow = new Date( );
            SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd_hh:mm:ss");
            writer.write(user.toString1()+" "+ft.format(dNow)+"\n");
            Log.d("save_user", user.toString1()+" "+ft.format(dNow));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void logout() {
        CurrentUser.setUser(null);
        CurrentUser.setLastLoginTime("2008-1-1 1:1:1");
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try{
            out = openFileOutput("user", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write("! " +CurrentUser.getLastLoginTime()+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected boolean loginTimeout(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        try{
            Date lastTime = format.parse(CurrentUser.getLastLoginTime());
            Date newTime = new Date();
            int days = (int) ((newTime.getTime() - lastTime.getTime()) / (1000*3600*24));
            //Log.d("----log in time----", String.valueOf(days));
            if(days > 30) {
                CurrentUser.setLastLoginTime(null);
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;

    }

    protected void loadLastUser() {
        FileInputStream in = null;
        BufferedReader reader = null;
        String[] content = null;
        try{
            in = openFileInput("user");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            String result = "";
            while((line = reader.readLine()) != null){
                result = line;
                Log.d("load_data", result);
            }
            content = result.split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        Log.d("load_user", String.valueOf(content.length));
        if(content == null || content[0].equals("!")) {
            CurrentUser.setUser(null);
            return;
        }

        if(CurrentUser.getUser() == null) {
            CurrentUser.setUser(new User());
        }
        CurrentUser.getUser().updateUser(content);
        CurrentUser.setLastLoginTime(content[4]);
        return;
    }

}