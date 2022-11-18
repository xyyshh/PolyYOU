package comp4342.android.polyyou.activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.bumptech.glide.util.Util;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import comp4342.android.polyyou.Config;
import comp4342.android.polyyou.R;
import comp4342.android.polyyou.biz.PostBiz;
import comp4342.android.polyyou.view.PostView;
import comp4342.android.polyyou.adapter.PostViewAdapter;
import comp4342.android.polyyou.model.Comment;
import comp4342.android.polyyou.model.Post;
import comp4342.android.polyyou.model.User;
import comp4342.android.polyyou.net.CommonCallBack;
import comp4342.android.polyyou.utils.T;


import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

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

    /** LinearLayout used for maintaining a list of Views that each display Posts. */
//    protected ListView m_vwPostLayout;
//    private ListView m_container;
//    // 初始化
//    public static void init(Context mContext) {
//        Config.USERID = SharedUtil.getString(mContext, "USERID");
//        if ( !Util.checkNULL(Config.USERID))
//            getUserInfo();
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent_welcome = new Intent(this, Welcome.class);
        startActivity(intent_welcome);

        RecyclerView mRecycleView = findViewById(R.id.postRecycleView);
//        RecyclerView mRecycleView = getLayoutInflater().inflate(R.layout.fragment_post,R.id.postRecycleView);
        //初始化数据
        initData();
        System.out.println("m_arrPostList num is "+m_arrPostList.size());
        //创建布局管理器，垂直设置LinearLayoutManager.VERTICAL，水平设置LinearLayoutManager.HORIZONTAL
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        创建适配器，将数据传递给适配器
        mAdapter = new PostViewAdapter(this, m_arrPostList);
        //设置布局管理器
//        mRecycleView.setLayoutManager(mLinearLayoutManager);
        //设置适配器adapter
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false));

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


//        initLayout(m_container);

//        registerForContextMenu(m_vwPostLayout);
//
//        Resources resources = getResources();

//        String[] strArray = resources.getStringArray(R.array.postList);
//        m_strAuthorName = resources.getString(R.string.author_name);
//
//        for (int i=0; i<strArray.length; i++) {
//            Post newPost = new Post(strArray[i], m_strAuthorName);
//            addPost(newPost);
//        }


    }
    /**
     * Method used to encapsulate the code that initializes and sets the Layout
     * for this Activity.
     */
    public void initData() {
//        for (int i = 1; i <= 40; i++) {
//            m_arrPostList.add(new Post());
//        }
//        ArrayList<Post> postList = new ArrayList<Post>();
        Post post1=new Post();
        User author = new User("Yooki", "1234");
        author.setHeadImage("hihih");
        post1.setAuthor(author);
        post1.setPostTitle("Help! I need the help!");
        post1.setPostContent("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        post1.setTag_name("1");
        Comment comment = new Comment("me","hha","2001-1226 12:00:00", "Cool!");
        post1.addComments(comment);
        m_arrPostList.add(post1);

        Post post2=new Post();
        User author1 = new User("haha11111111111111111111111111111111111111111111111111", "1235");
        author.setHeadImage("ihih");
        post2.setAuthor(author1);
        post2.setPostTitle("Hello!!");
        post2.setPostContent("ooooohohoo!");
        post2.setTag_name("2");
        Comment comment1 = new Comment("me","hha","2001-1226 12:00:00", "Cool!");
        post2.addComments(comment1);
        m_arrPostList.add(post2);
        m_arrPostList.add(post1);
        m_arrPostList.add(post2);
        m_arrPostList.add(post1);
        m_arrPostList.add(post2);
        m_arrPostList.add(post1);
        m_arrPostList.add(post2);
        m_arrPostList.add(post1);


//        m_arrPostList.add(new Post("2022-12-26 00:00:00", "Who can help me now aaaa aaaa aaaa aaa aaaaa aaa aaaaaa aaaaaa aaaaaa!","user name", "This is address", "This is the content of the post hahahahaha", "this is adreess"));
//        m_arrPostList.add(new Post("2022-12-26 00:00:00", "Who can help me now aaaa aaaa aaaa aaa aaaaa aaa aaaaaa aaaaaa aaaaaa!","user name", "This is address", "This is the content of the post hahahahaha", "this is adreess"));
//        m_arrPostList.add(new Post("2022-12-26 00:00:00", "Who can help me now aaaa aaaa aaaa aaa aaaaa aaa aaaaaa aaaaaa aaaaaa!","user name", "This is address", "This is the content of the post hahahahaha", "this is adreess"));
//        m_arrPostList.add(new Post("2022-12-26 00:00:00", "Who can help me now aaaa aaaa aaaa aaa aaaaa aaa aaaaaa aaaaaa aaaaaa!","user name", "This is address", "This is the content of the post hahahahaha", "this is adreess"));
//        m_arrPostList.add(new Post("2022-12-26 00:00:00", "Who can help me now aaaa aaaa aaaa aaa aaaaa aaa aaaaaa aaaaaa aaaaaa!","user name", "This is address", "This is the content of the post hahahahaha", "this is adreess"));

//        for(int i=0; i<m_arrPostList.size(); i++){
//            Post post=m_arrPostList.get(i);
//        }
    }

    public void initLayout() {
        btn_takeaway = findViewById(R.id.ta_filter_button);
        btn_help = findViewById(R.id.help_filter_button);
        btn_secondhand = findViewById(R.id.sh_filter_button);

    }
    private void initEvent() {
        T.init(Home.this);
        btn_secondhand.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                postBiz.loadPost("secondHand", new CommonCallBack<Post>() {
                    @Override
                    public void onError(Exception e) {
                        Log.d("second hand post get", "success");
                    }
                    @Override
                    public void onSuccess(Post response) {
                        stopLoadingProgress();
                        Log.d("login activity", "success");
                    }
                });
            }
        });

        btn_help.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
//                String email = etEmail.getText().toString();
//                String password = etPassword.getText().toString();
//                if(!Str.isValidEmail(email)){
//                    T.showToast("Please enter a polyu email address");  return;
//                }
//                else if(password.isEmpty()){
//                    T.showToast("Password cannot be empty");  return;
//                }
//                startLoadingProgress();
//                userBiz.login(email, password, new CommonCallBack<User>(){
//                    @Override
//                    public void onError(Exception e) {
//                        stopLoadingProgress();
//                        Log.d("login activity", e.getMessage());
//                    }
//
//                    @Override
//                    public void onSuccess(User response){
//                        stopLoadingProgress();
//                        Log.d("login activity", "success");
//                        toHome();
//                    }
//                });
            }
        });
        btn_takeaway.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });
    }

//    public void addView(ListView listView, Post post) {
//        PostView child = new PostView(this, post);
//        listView.addView(child);
//    }
}
