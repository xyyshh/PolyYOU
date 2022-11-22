package comp4342.android.polyyou.activity;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.resources.TextAppearance;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.biz.PostBiz;
import comp4342.android.polyyou.adapter.PostViewAdapter;
import comp4342.android.polyyou.model.Comment;
import comp4342.android.polyyou.model.CurrentUser;
import comp4342.android.polyyou.model.Data;
import comp4342.android.polyyou.model.Post;
import comp4342.android.polyyou.model.User;
import comp4342.android.polyyou.net.CommonCallBack;
import comp4342.android.polyyou.utils.T;


import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//public class Home extends AppCompatActivity {
public class Home extends BaseActivity {

    private Button btn_secondhand;
    private Button btn_help;
    private Button btn_takeaway;
    private PostBiz postBiz = new PostBiz();


    /** Contains the list Posts the Activity will present to the user. */
    private ArrayList<Post> m_arrPostList = new ArrayList<Post>();
    /**
     * Adapter used to bind an AdapterView to List of Posts.
     */
//    protected PostAdapter m_postAdapter;
    private PostViewAdapter mAdapter;
    private RecyclerView mRecycleView;
    private TextView noPostView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        verifyPermission(Home.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findAllPosts();
        // find whether the user has logged in in the last 30 days
        loadLastUser();
        if(CurrentUser.getUser() == null || loginTimeout()) {
            Intent intent_welcome = new Intent(this, Welcome.class);
            startActivity(intent_welcome);
        }
        noPostView = findViewById(R.id.noPostView);
        mRecycleView = findViewById(R.id.postRecycleView);
        initView();

        initLayout();
        initEvent();


        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        // Set that home is selected
        bottomNavigationView.setSelectedItemId(R.id.home);
        // Perform  ItemSelectedListener
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch (menuitem.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.add:
                        Intent intent = new Intent(Home.this, AddPost.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
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

    }

    private void toLoginActivity() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    public void verifyPermission(Context context){
        int permission = ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    Home.this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }
    /**
     * Method used to encapsulate the code that initializes and sets the Layout
     * for this Activity.
     */
    public void initData() {

    }

    public void initView() {
        if(m_arrPostList!=null){
            noPostView.setVisibility(View.INVISIBLE);
            mAdapter = new PostViewAdapter(Home.this, m_arrPostList);
            //设置适配器adapter
            mRecycleView.setAdapter(mAdapter);
            mRecycleView.setLayoutManager(new LinearLayoutManager(Home.this,
                    LinearLayoutManager.VERTICAL,false));
        }
        else{
            noPostView.setVisibility(View.VISIBLE);
        }
    }

    public void initLayout() {
        btn_takeaway = findViewById(R.id.ta_filter_button);
        btn_help = findViewById(R.id.help_filter_button);
        btn_secondhand = findViewById(R.id.sh_filter_button);

    }
//    private List<Comment> m_commentList = null;

    private void findAllPosts() {
//        m_commentList = null;
        // second hand = 1
        m_arrPostList = null;
        postBiz.loadPost("1", new CommonCallBack<Data>() {
            @Override
            public void onError(Exception e) {
                Log.e("second hand post get", e.toString());
            }
            @Override
            public void onSuccess(Data response) {
                stopLoadingProgress();
                Log.d("get_post_activity", response.getData());
                m_arrPostList = response.toArrayListPost();

//                int ind = 0;
//                for(; ind < m_arrPostList.size();ind++){
//                    Log.d("number_of_comment", String.valueOf(ind));
//                    postBiz.loadComment(String.valueOf(m_arrPostList.get(ind).getId()), new CommonCallBack<Data>() {
//                        @Override
//                        public void onError(Exception e) {
//                            Log.e("get_comments", "get comments error");
//                            T.showToast(e.toString());
//                        }
//
//                        @Override
//                        public void onSuccess(Data response) {
//                            List<Comment> lst = response.toArrayListComment();
//                            for(Comment comment: lst) {
//                                Log.d("load_comments", comment.toString());
//                                addComments(comment);
//                            }
//                        }
//                    });
//                }

            }
        });
    }
    private void initEvent() {
        T.init(Home.this);
        btn_secondhand.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                findAllPosts();
                ArrayList<Post> tmp = new ArrayList<Post>();
                if(m_arrPostList!=null){
                    for(Post post: m_arrPostList){
                        Log.d("post_id", post.toString());
                        if(post.getTag_id().toString().equals("1"))
                            tmp.add(post);
                    }
                    m_arrPostList = tmp;
                    initView();
                }

            }
        });
        btn_help.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                findAllPosts();
                ArrayList<Post> tmp = new ArrayList<Post>();
                if(m_arrPostList!=null){
                    for(Post post: m_arrPostList){
                        Log.d("post_id", post.toString());
                        if(post.getTag_id().toString().equals("2"))
                            tmp.add(post);
                    }
                    m_arrPostList = tmp;
                }

                initView();
            }
        });
        btn_takeaway.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                findAllPosts();
                ArrayList<Post> tmp = new ArrayList<Post>();
                if(m_arrPostList!=null){
                    for(Post post: m_arrPostList){
                        Log.d("post_id", post.toString());
                        if(post.getTag_id().toString().equals("3"))
                            tmp.add(post);
                    }
                    m_arrPostList = tmp;
                }

                initView();
            }
        });
    }
//    private void addComments(Comment comment) {
//        for (int i = 0; i < m_arrPostList.size(); i++) {
//            Post post = m_arrPostList.get(i);
//            Log.d("compare", String.valueOf(post.getId())+" "+comment.getPostId());
//            if (String.valueOf(post.getId()).equals(comment.getPostId()))
//                m_arrPostList.get(i).addComments(comment);
//        }
//    }
}
