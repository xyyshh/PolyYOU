package comp4342.android.polyyou.utils;

import android.os.CountDownTimer;

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
import comp4342.android.polyyou.model.Response;
import comp4342.android.polyyou.model.User;
import comp4342.android.polyyou.net.CommonCallBack;
import comp4342.android.polyyou.utils.Str;
import comp4342.android.polyyou.utils.T;

public class CountDownUtils extends CountDownTimer {
    private Button mSend;//倒计时按钮
    private int mNormal_txt_color;

    /**
     *
     * @param millisInFuture 倒计时总时长 单位毫秒
     * @param countDownInterval 每次减少量（步长）单位毫秒
     */
    public CountDownUtils(long millisInFuture, long countDownInterval, Button btnSend) {
        super(millisInFuture, countDownInterval);
        this.mSend = btnSend;
        //this.mNormal_txt_color=normal_txt_color;
        //在初始化的时候将按钮置灰，防止重复请求
        //setTextColor(pressed_txt_color);
        mSend.setClickable(false);//避免重复点击
    }

    @Override
    public void onTick(long millisUntilFinished) {
        Log.d("onTick", millisUntilFinished + "");
        mSend.setText(+millisUntilFinished / 1000+"s" );
    }
    /**
     * 计时结束
     */
    @Override
    public void onFinish() {
        //计时结束后将按钮的状态复原
        mSend.setText("VERIFY");
        mSend.setClickable(true);
        //setTextColor(mNormal_txt_color);
        this.cancel();
    }

//    private void setTextColor(int font_color) {
//        // ContextCompat.getColor(mContext,font_color); 可以用这个兼容方法，也可以自己手动判断
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
//            mSend.setTextColor(mContext.getColor(font_color));
//        } else {
//            mSend.setTextColor(mContext.getResources().getColor(font_color));
//        }
//    }
}