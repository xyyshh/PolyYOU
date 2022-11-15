package comp4342.android.polyyou.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.fragment.PostList;
import comp4342.android.polyyou.fragment.PostListAdapter;
import comp4342.android.polyyou.fragment.PostView;
import comp4342.android.polyyou.model.Post;

import android.app.Activity;


import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    /** Contains the list Posts the Activity will present to the user. */
    protected ArrayList<Post> m_arrPostList = new ArrayList<Post>();
    /**
     * Adapter used to bind an AdapterView to List of Posts.
     */
    protected PostListAdapter m_postAdapter;

    /** LinearLayout used for maintaining a list of Views that each display Posts. */
//    protected ListView m_vwPostLayout;
//    private ListView m_container;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ListView m_container = findViewById(R.id.postListViewGroup);
//        m_container = findViewById(R.id.postListViewGroup);

        Intent intent_welcome = new Intent(this, Welcome.class);
        startActivity(intent_welcome);

//        m_postAdapter = new PostListAdapter(getBaseContext(),m_arrPostList);
//        m_vwPostLayout.setAdapter(m_postAdapter);


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
        initLayout(m_container);

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
    public void initLayout(ListView listView) {
//        setContentView(R.layout.activity_home);
        m_arrPostList = new ArrayList<Post>();
        m_arrPostList.add(new Post("2022-12-26 00:00:00", "Who can help me now aaaa aaaa aaaa aaa aaaaa aaa aaaaaa aaaaaa aaaaaa!","user name", "This is address", "This is the content of the post hahahahaha", "this is adreess"));
        m_arrPostList.add(new Post("2022-12-26 00:00:00", "Who can help me now aaaa aaaa aaaa aaa aaaaa aaa aaaaaa aaaaaa aaaaaa!","user name", "This is address", "This is the content of the post hahahahaha", "this is adreess"));
        m_arrPostList.add(new Post("2022-12-26 00:00:00", "Who can help me now aaaa aaaa aaaa aaa aaaaa aaa aaaaaa aaaaaa aaaaaa!","user name", "This is address", "This is the content of the post hahahahaha", "this is adreess"));
        m_arrPostList.add(new Post("2022-12-26 00:00:00", "Who can help me now aaaa aaaa aaaa aaa aaaaa aaa aaaaaa aaaaaa aaaaaa!","user name", "This is address", "This is the content of the post hahahahaha", "this is adreess"));
        m_arrPostList.add(new Post("2022-12-26 00:00:00", "Who can help me now aaaa aaaa aaaa aaa aaaaa aaa aaaaaa aaaaaa aaaaaa!","user name", "This is address", "This is the content of the post hahahahaha", "this is adreess"));

        for(int i=0; i<m_arrPostList.size(); i++){
            Post post=m_arrPostList.get(i);
//            addView(listView, post);
        }
    }

    public void addView(ListView listView, Post post) {
        PostView child = new PostView(this, post);
        listView.addView(child);
    }
}
