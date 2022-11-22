package comp4342.android.polyyou.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import comp4342.android.polyyou.Config;
import comp4342.android.polyyou.R;
import comp4342.android.polyyou.adapter.PostViewAdapter;
import comp4342.android.polyyou.biz.PostBiz;
import comp4342.android.polyyou.model.Comment;
import comp4342.android.polyyou.model.CurrentUser;
import comp4342.android.polyyou.model.Data;
import comp4342.android.polyyou.model.Post;
import comp4342.android.polyyou.model.User;
import comp4342.android.polyyou.net.CommonCallBack;
import comp4342.android.polyyou.utils.T;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.zhy.http.okhttp.utils.L;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class Profile extends BaseActivity {

    private ImageView imgvProfile;
    private TextView tvUsername;
    private Button btnLogout;
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
    private ImageView profileImageView;
    private TextView noPostView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        noPostView  =findViewById(R.id.noPostView);
        profileImageView = findViewById(R.id.profile_pic);
//        profileImageView.setImageURI(Uri.parse(CurrentUser.getUser().getHeadImage()));

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set that home is selected
        bottomNavigationView.setSelectedItemId(R.id.profile);

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
                        startActivity(new Intent(getApplicationContext(), Notification.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        return true;
                }
                return false;
            }
        });

        mRecycleView = findViewById(R.id.postRecycleView);
        //初始化数据
        initData();

        if(m_arrPostList==null){
            noPostView.setVisibility(View.INVISIBLE);
            mAdapter = new PostViewAdapter(this, m_arrPostList);
            //设置适配器adapter
            mRecycleView.setAdapter(mAdapter);
            mRecycleView.setLayoutManager(new LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL,false));
            URL url = null;
            try {
                url = new URL(Config.baseUrl+"uploadTest.jpg");
                requestImg(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            noPostView.setVisibility(View.VISIBLE);
        }

        initView();
        initEvent();
        if(CurrentUser.getUser() != null)
            tvUsername.setText(CurrentUser.getUser().getName());
    }
    public void initData() {
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
                m_arrPostList = response.toArrayListPost();}
        });
    }

    private void requestImg(final URL imgUrl)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = null;
                try {
                    bitmap = BitmapFactory.decodeStream(imgUrl.openStream());

                    showImg(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void showImg(final Bitmap bitmap){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                profileImageView.setImageBitmap(bitmap);
            }
        });
    }

    protected void initView() {
        imgvProfile = findViewById(R.id.profile_pic);
        tvUsername = findViewById(R.id.profile_username);
        btnLogout = findViewById(R.id.button_logout);
    }

    protected void initEvent() {
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
                toHome();
            }
        });
    }

    private void toHome() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}