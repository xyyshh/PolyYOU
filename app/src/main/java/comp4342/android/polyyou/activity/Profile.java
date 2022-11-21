package comp4342.android.polyyou.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.adapter.PostViewAdapter;
import comp4342.android.polyyou.biz.PostBiz;
import comp4342.android.polyyou.model.Comment;
import comp4342.android.polyyou.model.CurrentUser;
import comp4342.android.polyyou.model.Post;
import comp4342.android.polyyou.model.User;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
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
        System.out.println("m_arrPostList num is "+m_arrPostList.size());
//        //创建布局管理器，垂直设置LinearLayoutManager.VERTICAL，水平设置LinearLayoutManager.HORIZONTAL
//        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        创建适配器，将数据传递给适配器
        mAdapter = new PostViewAdapter(this, m_arrPostList);
        //设置适配器adapter
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false));


        initView();
        initEvent();
        if(CurrentUser.getUser() != null) {
            tvUsername.setText(CurrentUser.getUser().getName());
        }
    }
    public void initData() {
//        for (int i = 1; i <= 40; i++) {
//            m_arrPostList.add(new Post());
//        }
//        ArrayList<Post> postList = new ArrayList<Post>();
        Post post1=new Post();
        User author = new User("Yooki", "1234", "", "");
        author.setHeadImage("hihih");
        post1.setAuthor(author);
        post1.setPostTitle("Help! I need the help!");
        post1.setPostContent("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        post1.setTag_name("1");
        Comment comment = new Comment(author,null,"Cool!");
        Comment comment1 = new Comment(author,null,"It looks soo cool! It looks soo cool! It looks soo cool! It looks soo cool! It looks soo cool! It looks soo cool! It looks soo cool! It looks soo cool! It looks soo cool!");
        Comment comment2 = new Comment(author,"hihi","It looks soo cool! It looks soo cool!It looks soo cool!It looks soo cool!It looks soo cool! It looks soo cool!It looks soo cool!It looks soo cool!");
        Comment comment3 = new Comment(author,null, "Cool!");
        post1.addComments(comment);
        post1.addComments(comment1);
        post1.addComments(comment2);
        post1.addComments(comment3);
        post1.addComments(comment2);
        post1.addComments(comment3);
        post1.addComments(comment2);
        post1.addComments(comment3);
        post1.addComments(comment2);
        post1.addComments(comment3);
        post1.setCurrentTime();
        m_arrPostList.add(post1);

        Post post2=new Post();
        User author1 = new User("haha11111111111111111111111111111111111111111111111111", "1235", "", "");
        author.setHeadImage("ihih");
        post2.setAuthor(author1);
        post2.setPostTitle("Hello!!");
        post2.setPostContent("ooooohohoo!");
        post2.setTag_name("2");
        post2.setCurrentTime();
        post2.addComments(comment1);
        m_arrPostList.add(post2);
        m_arrPostList.add(post1);
        m_arrPostList.add(post2);
        m_arrPostList.add(post1);
        m_arrPostList.add(post2);
        m_arrPostList.add(post1);
        m_arrPostList.add(post2);
        m_arrPostList.add(post1);
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