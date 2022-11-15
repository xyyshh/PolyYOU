package comp4342.android.polyyou.utils;

import android.content.Context;
import android.widget.Toast;

import comp4342.android.polyyou.activity.Signup;

public class T {
    public static void showToast(Context context, String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }
}
