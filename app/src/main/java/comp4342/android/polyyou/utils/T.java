package comp4342.android.polyyou.utils;

import android.content.Context;
import android.widget.Toast;

import comp4342.android.polyyou.activity.Signup;

public class T {

    private static Toast toast;

    public static void showToast(String s) {
        toast.setText(s);
        toast.show();
    }

    public static void init(Context context) {
        toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
    }
}
