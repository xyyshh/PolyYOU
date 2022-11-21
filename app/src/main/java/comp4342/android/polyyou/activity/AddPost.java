package comp4342.android.polyyou.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
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
    protected PostViewAdapter postAdapter;
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
                Long strPostTag = (long)0;
                if(topicGroup.getCheckedRadioButtonId()==btnSecondhand.getId()){
                    strPostTag = (long)1;
                }else if(topicGroup.getCheckedRadioButtonId()==btnHelp.getId()){
                    strPostTag = (long)2;
                }else if(topicGroup.getCheckedRadioButtonId()==btnTakeaway.getId()){
                    strPostTag = (long)3;
                }
                String strPostTitle = postTitleEditText.getText().toString();
                String strPostContent = postEditText.getText().toString();
                String strProfilePhotoAddress = "";
                String strUploadPhotoAddress = "";
//                if(topicGroup.getCheckedRadioButtonId()==-1){
//                    T.showToast("You should choose a tag!");  return;
//                }


                if (!strPostContent.isEmpty() && !strPostTitle.isEmpty()) {
//                    addPost(new Post(dateToStamp(System.currentTimeMillis()), strPostTitle, m_strUserName, strProfilePhotoAddress, strPostContent, strUploadPhotoAddress));
                    //postEditText.setText("");
                    Post post=new Post();
                    post.setAuthor(user);
                    post.setTime(dateToStamp(System.currentTimeMillis()));
                    post.setTag_id(strPostTag);
                    post.setPostContent(strPostContent);
                    post.setPostTitle(strPostTitle);

                    startLoadingProgress();
                    PostBiz.addpost(post, new CommonCallBack<Post>(){
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
                        }
                    });
//                    ????InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    ????imm.hideSoftInputFromWindow(postEditText.getWindowToken(), 0);
                }
            }
        });
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