package comp4342.android.polyyou.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import comp4342.android.polyyou.Config;
import comp4342.android.polyyou.R;
import comp4342.android.polyyou.net.UploadImage;
import comp4342.android.polyyou.utils.T;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignupImage extends AppCompatActivity {

    private Button btnUpload;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_image);
        initView();
        initEvent();
    }
    private void initView() {
        btnUpload = findViewById(R.id.upload_img);
        mImageView = findViewById(R.id.img);
    }
    private void initEvent() {
        T.init(SignupImage.this);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("---------", Environment.getExternalStorageDirectory().toString());
                String imagePath = "storage/emulated/0/DCIM/Camera/IMG_20221118_120225.jpg";
                Uri uri = Uri.fromFile(new File(imagePath));
                mImageView.setImageURI(uri);
                File file = new File(imagePath);
                if (!file.exists()) {
                    T.showToast("file does not exist");
                    return;
                }
//                Map<String, String> params = new HashMap<>();
//                params.put("username", "Jackyhhh");
//                params.put("password", "123");

                String url = Config.baseUrl+"login-center/image/";
                OkHttpUtils.post()
                        .addFile("image", "server_afu.png", file)
                        .url(url)
//                        .params(params)
                        .build()
                        .execute(new MyStringCallback());

                //////
//                try {
//                    String data = UploadImage.uploadImage(Config.baseUrl+"login-center/image/", imagePath);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }



//                try{
//                    OkHttpClient client = new OkHttpClient();
//                    File file = new File(imagePath);
//                    if (!file.exists()){
//                        T.showToast("file does not exist");
//                        System.out.println("doPostAImage失败");
//
//                    }else{
////                RequestBody requestBody2 = RequestBody.create(MediaType.parse("application/octet-stream"), file);
//
//
//
//                        RequestBody requestBody = new MultipartBody.Builder()
//                                //一定要设置这句
//                                .setType(MultipartBody.FORM)
//                                .addFormDataPart("image", imagePath, RequestBody.create(MediaType.parse("application/octet-stream"), file))
//                                .build();
//                        final Request request = new Request.Builder()
//                                .url(Config.baseUrl+"login-center/image/")
//                                .post(requestBody)
//                                .build();
//
//                        Response response=client.newCall(request).execute();//执行
////                Call call = client.newCall(request);
//                    }
//
//
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
            }
        });
    }


    public class MyStringCallback extends StringCallback {

        @Override
        public void onBefore(Request request, int id) {
            setTitle("loading...");
        }

        @Override
        public void onAfter(int id) {
            setTitle("Sample-okHttp");
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e("tag", "onResponse：complete");
            switch (id) {
                case 100:
                    T.showToast("http");
                    break;
                case 101:
                    T.showToast("https");
                    break;
            }
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            Log.e("tag", "inProgress:" + progress);
        }
    }
}