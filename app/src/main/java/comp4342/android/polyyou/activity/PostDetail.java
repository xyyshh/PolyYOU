package comp4342.android.polyyou.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.model.Post;
import comp4342.android.polyyou.net.CommonCallBack;
import comp4342.android.polyyou.utils.T;

public class PostDetail extends AppCompatActivity {
    private Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        initLayout();
        initEvent();
    }

    public void initLayout() {
        btn_back = findViewById(R.id.button_post_back);
    }
    private void initEvent() {
        T.init(PostDetail.this);
        btn_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostDetail.this, Home.class);
                startActivity(intent);
            }

        });
    }
}