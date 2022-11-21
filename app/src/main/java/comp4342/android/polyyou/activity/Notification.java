

package comp4342.android.polyyou.activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.adapter.PostViewAdapter;
import comp4342.android.polyyou.biz.PostBiz;
import comp4342.android.polyyou.model.Comment;
import comp4342.android.polyyou.model.Post;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import comp4342.android.polyyou.model.CurrentUser;
import comp4342.android.polyyou.model.User;
import comp4342.android.polyyou.net.CommonCallBack;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Notification extends BaseActivity {
    private PostBiz postBiz = new PostBiz();

    private ArrayList<Post> m_arrPostList = new ArrayList<Post>();
    private ArrayList<Post> m_arrCommentList = new ArrayList<Post>();
    private PostViewAdapter mAdapter;
    private RecyclerView commentUnderPost;
    private RecyclerView commentUnderComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        initData();
        commentUnderPost = findViewById(R.id.postNotificationRecycleView);
        commentUnderComment = findViewById(R.id.commentNotificationRecycleView);
//        //创建布局管理器，垂直设置LinearLayoutManager.VERTICAL，水平设置LinearLayoutManager.HORIZONTAL
//        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        创建适配器，将数据传递给适配器
        mAdapter = new PostViewAdapter(this, m_arrPostList);
        mAdapter = new PostViewAdapter(this, m_arrCommentList);
        //设置适配器adapter
        commentUnderPost.setAdapter(mAdapter);
        commentUnderPost.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false));
        commentUnderComment.setAdapter(mAdapter);
        commentUnderComment.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false));

        get_notify();


        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set that home is selected
        bottomNavigationView.setSelectedItemId(R.id.notification);

        // Perform  ItemSelectedListener
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch (menuitem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.add:
                        startActivity(new Intent(getApplicationContext(), AddPost.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.notification:
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });


//        Intent intent = new Intent(this, Home.class);
//        startActivity(intent);
    }

    public void initData() {
//        User author = new User("Jehan","123","","");
        User author=CurrentUser.getUser();
        Post post1=new Post();
//        author.setHeadImage("hihih");
        post1.setAuthor(author);
        post1.setPostTitle("Jehan. Help! I need the help!");
        post1.setPostContent("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        post1.setTag_name("1");
        Comment comment = new Comment(author,null,"Cool!");
        Comment comment1 = new Comment(author,null,"It looks soo cool! It looks soo cool! It looks soo cool! It looks soo cool! It looks soo cool! It looks soo cool! It looks soo cool! It looks soo cool! It looks soo cool!");
        Comment comment2 = new Comment(author,"Yooki","It looks soo cool! It looks soo cool!It looks soo cool!It looks soo cool!It looks soo cool! It looks soo cool!It looks soo cool!It looks soo cool!");
        post1.addComments(comment);
        post1.addComments(comment1);
        post1.addComments(comment2);
        post1.setCurrentTime();
        m_arrPostList.add(post1);
        m_arrCommentList.add(post1);
    }
    public void get_notify(){
        //T.init(Notification.this);
        startLoadingProgress();
        postBiz.loadPostByNotification(CurrentUser.getUser(),new CommonCallBack<Post>(){
            @Override
            public void onError(Exception e) {
                stopLoadingProgress();
                Log.d("load post notification", e.getMessage());

            }
            @Override
            public void onSuccess(Post response) {
                stopLoadingProgress();
                Log.d("login activity",  response.toString());
            }
        });
    }
}