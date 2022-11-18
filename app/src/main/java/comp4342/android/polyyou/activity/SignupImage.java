package comp4342.android.polyyou.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

import comp4342.android.polyyou.Config;
import comp4342.android.polyyou.R;
import comp4342.android.polyyou.utils.T;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignupImage extends AppCompatActivity {

    private Button btnUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_image);
        initView();
        initEvent();
    }
    private void initView() {
        btnUpload = findViewById(R.id.upload_img);
    }
    private void initEvent() {
        T.init(SignupImage.this);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    OkHttpClient client = new OkHttpClient();
                    File file = new File("/storage/emulated/0/DCIM/Camera/IMG_20221118_120225.jpg");
                    if (!file.exists()){
                        T.showToast("file does not exist");
                        System.out.println("doPostAImage失败");

                    }else{
//                RequestBody requestBody2 = RequestBody.create(MediaType.parse("application/octet-stream"), file);



                        RequestBody muiltipartBody = new MultipartBody.Builder()
                                //一定要设置这句
                                .setType(MultipartBody.FORM)
                                .addFormDataPart("image", "imageOut.png", RequestBody.create(MediaType.parse("application/octet-stream"), file))
                                .build();
                        final Request request = new Request.Builder()
                                .url(Config.baseUrl+"login-center/images/")
                                .post(muiltipartBody)
                                .build();

                        Response response=client.newCall(request).execute();//执行
//                Call call = client.newCall(request);
                    }



                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}