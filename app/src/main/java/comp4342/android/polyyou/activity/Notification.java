package comp4342.android.polyyou.activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.adapter.PostViewAdapter;
import comp4342.android.polyyou.biz.PostBiz;
import comp4342.android.polyyou.model.Comment;
import comp4342.android.polyyou.model.Data;
import comp4342.android.polyyou.model.Post;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import comp4342.android.polyyou.model.CurrentUser;
import comp4342.android.polyyou.model.User;
import comp4342.android.polyyou.net.CommonCallBack;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Notification extends BaseActivity {
    private PostBiz postBiz = new PostBiz();

    private ArrayList<Post> m_arrPostList = new ArrayList<>();
    //    private ArrayList<Post> m_arrCommentList = new ArrayList<Post>();
    private PostViewAdapter mAdapter;
    private RecyclerView commentUnderPost;
    private TextView notificationView;
    private TextView noNotificationsView;
//    private RecyclerView commentUnderComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        initLayout();
        loadNotificationPosts(CurrentUser.getUser().getId());

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        // Set that home is selected
        bottomNavigationView.setSelectedItemId(R.id.notification);
        // Perform  ItemSelectedListener
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                switch (menuitem.getItemId()) {
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
    }

    private void loadNotificationPosts(String owner_id) {
        m_arrPostList = null;
        // second hand = 1
        postBiz.loadPostByNotification(new CommonCallBack<Data>() {
            @Override
            public void onError(Exception e) {
                Log.e("notification post get", e.toString());
            }

            @Override
            public void onSuccess(Data response) {
                stopLoadingProgress();
                Log.d("get_post_activity", response.getData());
                m_arrPostList = response.toArrayListPost();
                initView();
            }
        });
    }

    private void initView() {
        if(m_arrPostList!=null){
            notificationView.setVisibility(View.INVISIBLE);
            mAdapter = new PostViewAdapter(Notification.this, m_arrPostList);
            //设置适配器adapter
            commentUnderPost.setAdapter(mAdapter);
            commentUnderPost.setLayoutManager(new LinearLayoutManager(Notification.this,
                    LinearLayoutManager.VERTICAL,false));
        }
        else{
            notificationView.setVisibility(View.VISIBLE);
        }
    }

    private void initLayout() {
        commentUnderPost = findViewById(R.id.postNotificationRecycleView);
        notificationView = findViewById(R.id.post_notification);
        noNotificationsView = findViewById(R.id.noNotificationView);
        //No notification
        if (m_arrPostList == null) {
            noNotificationsView.setVisibility(View.VISIBLE);
            commentUnderPost.setVisibility(View.INVISIBLE);
            commentUnderPost.setVisibility(View.INVISIBLE);
        } else {
            noNotificationsView.setVisibility(View.INVISIBLE);
            commentUnderPost.setVisibility(View.VISIBLE);
            commentUnderPost.setVisibility(View.VISIBLE);
            mAdapter = new PostViewAdapter(this, m_arrPostList);

            commentUnderPost.setAdapter(mAdapter);
            commentUnderPost.setLayoutManager(new LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL, false));

        }
    }
}


