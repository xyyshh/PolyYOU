package comp4342.android.polyyou.activity;

import androidx.activity.result.ActivityResultCallback;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import comp4342.android.polyyou.R;


public class ImageTest extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagetest);
        Log.d("11", "11");
        Button btnUpload = findViewById(R.id.button2);
        mImageView = findViewById(R.id.imageView);
        Log.d("22", "22");
        btnUpload.setOnClickListener(v -> mGetContent.launch("image/*"));

    }

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if (result != null)
                        mImageView.setImageURI(result);

        }
    }
    );

}