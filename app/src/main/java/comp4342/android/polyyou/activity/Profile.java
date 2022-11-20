package comp4342.android.polyyou.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.model.CurrentUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends BaseActivity {

    private ImageView imgvProfile;
    private TextView tvUsername;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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

        initView();
        initEvent();
        if(CurrentUser.getUser() != null)
            tvUsername.setText(CurrentUser.getUser().getName() + CurrentUser.getUser().getId());
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