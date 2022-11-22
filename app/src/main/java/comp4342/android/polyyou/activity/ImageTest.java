package comp4342.android.polyyou.activity;

import androidx.activity.result.ActivityResultCallback;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import comp4342.android.polyyou.R;


public class ImageTest extends AppCompatActivity {

    private ImageView mImageView;
    private Button btnUploadImage;
    private Button btnImageNext;
    private boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_image);
        Log.d("11", "11");
        mImageView = findViewById(R.id.profile_pic);
        btnUploadImage = findViewById(R.id.add_profile_pic);
        btnImageNext = findViewById(R.id.button_image_next);
        Log.d("22", "22");
        btnUploadImage.setOnClickListener(v -> mGetContent.launch("image/*"));
        btnImageNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toHome();
            }
        });
//        btnUploadImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                View v = new View();
//                if(flag){
//                    v = mGetContent.launch("image/*");
//                }
//            }
//        });

//        btnUploadImage.setOnClickListener(v -> mGetContent.launch("image/*"));
//        if (isShowOrNot == false) {
//            btnUploadImage.setVisibility(View.VISIBLE); // 设置显示
//            isShowOrNot = true;
//        }else {
//            btnUploadImage.setVisibility(View.GONE); // 设置隐藏
//            isShowOrNot  = false;
//        }
//        btnUploadImage.setBackgroundTintMode(Color.parseColor("#F2F2F2"));
//        btnUploadImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                btnUploadImage.setBackgroundColor(Color.parseColor("#F2F2F2"));
//            }
//        });


    }

    private void toHome() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
    ActivityResultLauncher<String> mGetContent = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if (result != null)
                        btnImageNext.setText("Next");
                        mImageView.setImageURI(result);

        }
    }
    );

}