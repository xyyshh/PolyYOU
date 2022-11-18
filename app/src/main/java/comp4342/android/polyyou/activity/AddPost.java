package comp4342.android.polyyou.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.adapter.PostViewAdapter;
import comp4342.android.polyyou.model.Post;
import comp4342.android.polyyou.adapter.PostViewAdapter;
import comp4342.android.polyyou.model.User;
import comp4342.android.polyyou.net.CommonCallBack;
import comp4342.android.polyyou.biz.PostBiz;

public class AddPost extends AppCompatActivity {

    protected String m_strUserName;
    protected EditText postEditText;
    protected EditText postTitleEditText;
    protected Button btnPost;
    private RadioGroup topicGroup;
    private RadioButton btnTopic;
    protected PostViewAdapter postAdapter;
    //User user = new User();

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
        //initAddPostListeners();
    }


    public void initLayout() {
        topicGroup = (RadioGroup)findViewById(R.id.topic_group) ;
        btnTopic = (RadioButton)findViewById(topicGroup.getCheckedRadioButtonId());
        //onRadioButtonClicked(findViewById(R.id.topic_group));
        postTitleEditText = findViewById(R.id.post_title_input);
        postEditText = findViewById(R.id.post_text_input);
        btnPost = findViewById(R.id.button_confirm_post);
    }

    protected void initAddPostListeners() {
        //点击post button
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strPostTag = btnTopic.toString();
                String strPostTitle = postTitleEditText.getText().toString();
                String strPostContent = postEditText.getText().toString();
                String strProfilePhotoAddress = "";
                String strUploadPhotoAddress = "";
                if (!strPostContent.isEmpty() && !strPostTitle.isEmpty()) {
//                    addPost(new Post(dateToStamp(System.currentTimeMillis()), strPostTitle, m_strUserName, strProfilePhotoAddress, strPostContent, strUploadPhotoAddress));
                    //postEditText.setText("");
                    Post post=new Post();
//                    post.setAuthor();
                    post.setTime(dateToStamp(System.currentTimeMillis()));
                    post.setTag_name(strPostTag);
                    post.setPostContent(strPostContent);
                    post.setPostTitle(strPostTitle);

                    PostBiz.addpost(post, new CommonCallBack<Post>(){
                        public void onError(Exception e) {
                            Log.d("add post activity", e.getMessage());
                        }

                        @Override
                        public void onSuccess(Post response) {
                            Log.d("add post", "success");
                            startActivity(new Intent(getApplicationContext(), Home.class));
                            toHome();
                        }
                    });
//                    ????InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    ????imm.hideSoftInputFromWindow(postEditText.getWindowToken(), 0);
                }
            }
        });
    }
    private void toHome() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
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