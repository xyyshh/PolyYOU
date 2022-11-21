//package comp4342.android.polyyou.activity;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//
//import android.Manifest;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Environment;
//import android.provider.MediaStore;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//
//import com.zhy.http.okhttp.OkHttpUtils;
//import com.zhy.http.okhttp.callback.StringCallback;
//
//import org.json.JSONException;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.Map;
//
//import comp4342.android.polyyou.Config;
//import comp4342.android.polyyou.R;
//import comp4342.android.polyyou.net.UploadImage;
//import comp4342.android.polyyou.utils.T;
//import okhttp3.Call;
//import okhttp3.MediaType;
//import okhttp3.MultipartBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//
//public class SignupImage extends AppCompatActivity {
//
//    private Button btnUpload;
//    private ImageView mImageView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signup_image);
//        initView();
//        initEvent();
//    }
//    private void initView() {
//        btnUpload = findViewById(R.id.upload_img);
//        mImageView = findViewById(R.id.img);
//    }
//    private void initEvent() {
//        T.init(SignupImage.this);
//        btnUpload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("---------", Environment.getExternalStorageDirectory().toString());
//                String imagePath = "storage/emulated/0/Pictures/IMG_20221118_180839.jpg";
//                Uri uri = Uri.fromFile(new File(imagePath));
//                mImageView.setImageURI(uri);
//                File file = new File(imagePath);
//                if (!file.exists()) {
//                    T.showToast("file does not exist");
//                    return;
//                }
//                String url = Config.baseUrl+"login-center/register-image/";
//                OkHttpUtils.post()
//                        .addParams("email", "20074794d@connect.polyu.hk")
//                        .addFile("image", "server_afu.png", file)
//                        .url(url)
//                        .build()
//                        .execute(new MyStringCallback());
//            }
//        });
//    }
//
//
//    public class MyStringCallback extends StringCallback {
//
//        @Override
//        public void onBefore(Request request, int id) {
//            setTitle("loading...");
//        }
//
//        @Override
//        public void onAfter(int id) {
//            setTitle("Sample-okHttp");
//        }
//
//        @Override
//        public void onError(Call call, Exception e, int id) {
//            e.printStackTrace();
//        }
//
//        @Override
//        public void onResponse(String response, int id) {
//            Log.e("tag", "onResponse：complete");
//            switch (id) {
//                case 100:
//                    T.showToast("http");
//                    break;
//                case 101:
//                    T.showToast("https");
//                    break;
//            }
//        }
//
//        @Override
//        public void inProgress(float progress, long total, int id) {
//            Log.e("tag", "inProgress:" + progress);
//        }
//    }
//}