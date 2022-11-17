package comp4342.android.polyyou.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.adapter.PostAdapter;
import comp4342.android.polyyou.fragment.PostView;
import comp4342.android.polyyou.adapter.PostViewAdapter;
import comp4342.android.polyyou.model.Post;


import android.widget.ListView;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    /** Contains the list Posts the Activity will present to the user. */
    protected ArrayList<Post> m_arrPostList = new ArrayList<Post>();
    /**
     * Adapter used to bind an AdapterView to List of Posts.
     */
    protected PostAdapter m_postAdapter;
    private PostViewAdapter mAdapter;

    /** LinearLayout used for maintaining a list of Views that each display Posts. */
//    protected ListView m_vwPostLayout;
//    private ListView m_container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        ListView m_container = findViewById(R.id.postListViewGroup);
//        m_container = findViewById(R.id.postListViewGroup);

        Intent intent_welcome = new Intent(this, Welcome.class);
        startActivity(intent_welcome);
        //setContentView(R.layout.post_page);


        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

//
//        // Set that home is selected
        bottomNavigationView.setSelectedItemId(R.id.home);

//
//        // Perform  ItemSelectedListener
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

        RecyclerView mRecycleView = findViewById(R.id.postRecycleView);
        //初始化数据
        initData(m_arrPostList);
        //创建布局管理器，垂直设置LinearLayoutManager.VERTICAL，水平设置LinearLayoutManager.HORIZONTAL
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        创建适配器，将数据传递给适配器
        mAdapter = new PostViewAdapter(this, m_arrPostList);
        //设置布局管理器
        mRecycleView.setLayoutManager(mLinearLayoutManager);
        //设置适配器adapter
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,false));

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
    public void initData(ArrayList<Post> m_arrPostList) {
//        for (int i = 1; i <= 40; i++) {
//            m_arrPostList.add(new Post());
//        }
        m_arrPostList = new ArrayList<Post>();
//        m_arrPostList.add(new Post("2022-12-26 00:00:00", "Who can help me now aaaa aaaa aaaa aaa aaaaa aaa aaaaaa aaaaaa aaaaaa!","user name", "This is address", "This is the content of the post hahahahaha", "this is adreess"));
//        m_arrPostList.add(new Post("2022-12-26 00:00:00", "Who can help me now aaaa aaaa aaaa aaa aaaaa aaa aaaaaa aaaaaa aaaaaa!","user name", "This is address", "This is the content of the post hahahahaha", "this is adreess"));
//        m_arrPostList.add(new Post("2022-12-26 00:00:00", "Who can help me now aaaa aaaa aaaa aaa aaaaa aaa aaaaaa aaaaaa aaaaaa!","user name", "This is address", "This is the content of the post hahahahaha", "this is adreess"));
//        m_arrPostList.add(new Post("2022-12-26 00:00:00", "Who can help me now aaaa aaaa aaaa aaa aaaaa aaa aaaaaa aaaaaa aaaaaa!","user name", "This is address", "This is the content of the post hahahahaha", "this is adreess"));
//        m_arrPostList.add(new Post("2022-12-26 00:00:00", "Who can help me now aaaa aaaa aaaa aaa aaaaa aaa aaaaaa aaaaaa aaaaaa!","user name", "This is address", "This is the content of the post hahahahaha", "this is adreess"));

//        for(int i=0; i<m_arrPostList.size(); i++){
//            Post post=m_arrPostList.get(i);
//        }
    }
//    public void initLayout(ListView listView) {
////        setContentView(R.layout.activity_home);
//        m_arrPostList = new ArrayList<Post>();
//        m_arrPostList.add(new Post("2022-12-26 00:00:00", "Who can help me now aaaa aaaa aaaa aaa aaaaa aaa aaaaaa aaaaaa aaaaaa!","user name", "This is address", "This is the content of the post hahahahaha", "this is adreess"));
//        m_arrPostList.add(new Post("2022-12-26 00:00:00", "Who can help me now aaaa aaaa aaaa aaa aaaaa aaa aaaaaa aaaaaa aaaaaa!","user name", "This is address", "This is the content of the post hahahahaha", "this is adreess"));
//        m_arrPostList.add(new Post("2022-12-26 00:00:00", "Who can help me now aaaa aaaa aaaa aaa aaaaa aaa aaaaaa aaaaaa aaaaaa!","user name", "This is address", "This is the content of the post hahahahaha", "this is adreess"));
//        m_arrPostList.add(new Post("2022-12-26 00:00:00", "Who can help me now aaaa aaaa aaaa aaa aaaaa aaa aaaaaa aaaaaa aaaaaa!","user name", "This is address", "This is the content of the post hahahahaha", "this is adreess"));
//        m_arrPostList.add(new Post("2022-12-26 00:00:00", "Who can help me now aaaa aaaa aaaa aaa aaaaa aaa aaaaaa aaaaaa aaaaaa!","user name", "This is address", "This is the content of the post hahahahaha", "this is adreess"));
//
//        for(int i=0; i<m_arrPostList.size(); i++){
//            Post post=m_arrPostList.get(i);
////            addView(listView, post);
//        }
//    }

    public void addView(ListView listView, Post post) {
        PostView child = new PostView(this, post);
        listView.addView(child);
    }
}
