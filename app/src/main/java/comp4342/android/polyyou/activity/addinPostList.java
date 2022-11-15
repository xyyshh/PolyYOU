package comp4342.android.polyyou.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import comp4342.android.polyyou.R;

public class addinPostList extends AppCompatActivity {

        private LinearLayout mContainer;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);
            mContainer = mContainer.findViewById(R.id.post_area);
        }

        /**
         * 按钮点击事件，向容器中添加TextView
         * @param view
         */
        public void addView(View view) {
            TextView child = new TextView(this);
            child.setTextSize(20);
            child.setTextColor(getResources().getColor(R.color.colorAccent));
            // 调用一个参数的addView方法
            mContainer.addView(child);
        }


}
