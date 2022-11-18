package comp4342.android.polyyou.model;

import android.app.AppComponentFactory;
import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class CurrentUser extends AppCompatActivity {

    private static User curUser;

    public static User getUser() {
        return curUser;
    }

    public static void setUser(User user) {
        curUser = user;
    }
}
