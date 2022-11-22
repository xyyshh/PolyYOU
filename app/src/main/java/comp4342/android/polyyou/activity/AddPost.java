package comp4342.android.polyyou.activity;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.adapter.PostViewAdapter;
import comp4342.android.polyyou.model.Post;
import comp4342.android.polyyou.model.User;
import comp4342.android.polyyou.net.CommonCallBack;
import comp4342.android.polyyou.biz.PostBiz;
import comp4342.android.polyyou.model.CurrentUser;
import comp4342.android.polyyou.utils.T;

public class AddPost extends BaseActivity {

    protected String m_strUserName;
    protected EditText postEditText;
    protected EditText postTitleEditText;
    protected Button btnPost;
    private RadioGroup topicGroup;
    private RadioButton btnTopic;
    private RadioButton btnSecondhand;
    private RadioButton btnHelp;
    private RadioButton btnTakeaway;
    private Button btnImage;
    private ImageView imageView;
    protected PostViewAdapter postAdapter;
    private Uri imageUri;
    User user = CurrentUser.getUser();

    private PostBiz PostBiz = new PostBiz();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.add);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch (menuitem.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.add:
                        return true;
                    case R.id.notification:
                        startActivity(new Intent(getApplicationContext(), Notification.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        initLayout();
        initAddPostListeners();
        initChooseImage();
    }


    public void initLayout() {
        topicGroup = (RadioGroup)findViewById(R.id.topic_group) ;
        btnTopic = (RadioButton)findViewById(topicGroup.getCheckedRadioButtonId());
        //onRadioButtonClicked(findViewById(R.id.topic_group));
        postTitleEditText = findViewById(R.id.post_title_input);
        postEditText = findViewById(R.id.post_text_input);
        btnPost = findViewById(R.id.button_confirm_post);
        btnSecondhand = findViewById(R.id.sh_button);
        btnHelp = findViewById(R.id.help_button);
        btnTakeaway = findViewById(R.id.ta_button);
        btnImage = findViewById(R.id.button_choose_image);
        imageView = findViewById(R.id.imageview_post_image);
    }

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if (result != null) {
                        imageView.setImageURI(result);
                        imageUri = result;
                    }
                }
            }
    );

    protected void initChooseImage() {
        T.init(AddPost.this);
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGetContent.launch("image/*");
            }
        });
    }

    protected void initAddPostListeners() {
        T.init(AddPost.this);

//        btnSecondhand.setOnClickListener(new View.OnClickListener(){
//
//        });
        //点击post button
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(topicGroup.getCheckedRadioButtonId()==-1){
                    T.showToast("You need to choose a tag!");
                    return;
                }
                if(postTitleEditText.getText().toString().equals("")){
                    T.showToast("Post's title cannot be empty");
                    return;
                }
                if(postEditText.getText().toString().equals("")){
                    T.showToast("Post's content cannot be empty");
                    return;
                }
                String strPostTag = "";
                if(topicGroup.getCheckedRadioButtonId()==btnSecondhand.getId()){
                    strPostTag = "1";
                }else if(topicGroup.getCheckedRadioButtonId()==btnHelp.getId()){
                    strPostTag = "2";
                }else if(topicGroup.getCheckedRadioButtonId()==btnTakeaway.getId()){
                    strPostTag = "3";
                }
                String strPostTitle = postTitleEditText.getText().toString();
                String strPostContent = postEditText.getText().toString();

                if (!strPostContent.isEmpty() && !strPostTitle.isEmpty()) {
                    Post post=new Post();
                    post.setAuthor(user);
                    post.setTime(dateToStamp(System.currentTimeMillis()));
                    post.setTag_id(strPostTag);
                    post.setPostContent(strPostContent);
                    post.setPostTitle(strPostTitle);

                    startLoadingProgress();
                    PostBiz.addPost(post, uriToFileApiQ(imageUri, AddPost.this), new CommonCallBack<Post>(){
                        @Override
                        public void onError(Exception e) {
                            stopLoadingProgress();
                            T.showToast("Post failed");
                            Log.d("add post activity", e.getMessage());
                        }

                        @Override
                        public void onSuccess(Post response) {
                            stopLoadingProgress();
                            Log.d("add post", "success");
                            toHome(AddPost.this);
                        }
                    });
//                    ????InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    ????imm.hideSoftInputFromWindow(postEditText.getWindowToken(), 0);
                }
            }
        });
    }

    public void toHome(Context context){
        Intent intent = new Intent(context, Home.class);
        startActivity(intent);
    }
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


    protected String dateToStamp(long s) {
        String res;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(s);
            res = simpleDateFormat.format(date);
        } catch (Exception e) {
            return "";
        }
        return res;
    }

}