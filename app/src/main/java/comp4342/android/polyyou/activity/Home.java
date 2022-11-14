package comp4342.android.polyyou.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import comp4342.android.polyyou.R;
import android.app.Activity;


import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

public class Home extends AppCompatActivity {
//    private View _bg__post_page_ek2;
//    private View post_area_ek1;
//    private TextView comment_number;
//    private ImageView comment_icon;
//    private TextView post_content;
//    private ImageView user_profile_photo;
//    private TextView time;
//    private TextView user_name;
//    private TextView post_title;
//    private View bottom_bar_background;
//    private ImageView vector;
//    private ImageView vector_ek1;
//    private ImageView vector_ek2;
//    private ImageView vector_ek3;
//    private View psw_input;
//    private ImageView vector_ek4;
//    private TextView type_something___________;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent_welcome = new Intent(this, Welcome.class);
        startActivity(intent_welcome);
        //setContentView(R.layout.post_page);


        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        View search_input = findViewById(R.id.search_input);
        ImageView search_icon = findViewById(R.id.search_icon);
        TextView type_something___________ = (TextView) findViewById(R.id.type_something);


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






//                _bg__post_page_ek2 = (View) findViewById(R.id._bg__post_page_ek2);
//                post_area_ek1 = (View) findViewById(R.id.post_area_ek1);
//                comment_number = (TextView) findViewById(R.id.comment_number);
//                comment_icon = (ImageView) findViewById(R.id.comment_icon);
//                post_content = (TextView) findViewById(R.id.post_content);
//                user_profile_photo = (ImageView) findViewById(R.id.user_profile_photo);
//                time = (TextView) findViewById(R.id.time);
//                user_name = (TextView) findViewById(R.id.user_name);
//                post_title = (TextView) findViewById(R.id.post_title);
//                bottom_bar_background = (View) findViewById(R.id.bottom_bar_background);
//                vector = (ImageView) findViewById(R.id.vector);
//                vector_ek1 = (ImageView) findViewById(R.id.vector_ek1);
//                vector_ek2 = (ImageView) findViewById(R.id.vector_ek2);
//                vector_ek3 = (ImageView) findViewById(R.id.vector_ek3);
//                psw_input = (View) findViewById(R.id.psw_input);
//                vector_ek4 = (ImageView) findViewById(R.id.vector_ek4);
//                type_something___________ = (TextView) findViewById(R.id.type_something___________);


                //custom code goes here

            }
}
