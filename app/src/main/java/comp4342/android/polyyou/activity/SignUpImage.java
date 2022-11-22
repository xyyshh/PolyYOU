package comp4342.android.polyyou.activity;

import androidx.activity.result.ActivityResultCallback;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;


import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.biz.UserBiz;
import comp4342.android.polyyou.model.CurrentUser;
import comp4342.android.polyyou.model.User;
import comp4342.android.polyyou.net.CommonCallBack;
import comp4342.android.polyyou.utils.T;


public class SignUpImage extends BaseActivity {

    private ImageView mImageView;
    private Button btnUploadImage;
    private Button btnImageNext;
    private Uri imageUri;
    private UserBiz userBiz = new UserBiz();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_image);
        mImageView = findViewById(R.id.profile_pic);
        btnUploadImage = findViewById(R.id.add_profile_pic);
        btnImageNext = findViewById(R.id.button_image_next);
        btnUploadImage.setOnClickListener(v -> mGetContent.launch("image/*"));
        btnImageNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUri == null) {
                    Log.d("Skip upload image", "success");
                    toHome();
                } else {
                    startLoadingProgress();
                    userBiz.updateProfileImage(CurrentUser.getUser().getEmail(), uriToFileApiQ(imageUri, SignUpImage.this), new CommonCallBack<User>() {
                        @Override
                        public void onError(Exception e) {
                            stopLoadingProgress();
                            T.showToast("Upload failed");
                            Log.d("add profile image", e.getMessage());
                        }

                        @Override
                        public void onSuccess(User response) {
                            stopLoadingProgress();
                            Log.d("add profile image", "success");
                            toHome();
                        }
                    });
                }
            }
        });
    }

    private void toHome() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
    ActivityResultLauncher<String> mGetContent = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onActivityResult(Uri result) {
                    if (result != null) {
                        btnImageNext.setText("Next");
                        mImageView.setImageURI(result);
                        imageUri = result;
                    }
                }
            }
    );
    public static File uriToFileApiQ(Uri uri, Context context) {

        File file = null;
        if (uri == null) return null;
        //android10以上转换
        if (uri.getScheme().equals(ContentResolver.SCHEME_FILE)) {
            file = new File(uri.getPath());
        } else if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
            //把文件复制到沙盒目录
            ContentResolver contentResolver = context.getContentResolver();
            String displayName = System.currentTimeMillis() + Math.round((Math.random() + 1) * 1000)
                    + "." + MimeTypeMap.getSingleton().getExtensionFromMimeType(contentResolver.getType(uri));
            try {
                InputStream is = contentResolver.openInputStream(uri);
                File cache = new File(context.getCacheDir().getAbsolutePath(), displayName);
                FileOutputStream fos = new FileOutputStream(cache);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    FileUtils.copy(is, fos);
                }
                file = cache;
                fos.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

}