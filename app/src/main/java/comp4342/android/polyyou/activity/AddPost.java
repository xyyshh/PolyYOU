package comp4342.android.polyyou.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.model.Post;
import comp4342.android.polyyou.fragment.PostListAdapter;

public class AddPost extends AppCompatActivity{

    /**
     * Contains the name of the Author for the posts.
     */
    protected String m_strUserName;

    /** EditText used for entering text for a new Post to be added to m_arrPostList. */
    protected EditText m_vwPostEditText;
    protected EditText m_vwPostTitleEditText;

    /** Button used for creating and adding a new Post to m_arrPostList using the
     * text entered in m_vwPostEditText. */
    protected Button m_vwPostButton;
    protected PostListAdapter m_postAdapter;
    protected ArrayList<Post> m_arrPostList = new ArrayList<Post>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set that home is selected
        bottomNavigationView.setSelectedItemId(R.id.add);

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

        Resources resources = getResources();

        String[] strArray = resources.getStringArray(R.array.postList);
        m_strUserName = resources.getString(R.string.user_name_string);
        String strPostContent = m_vwPostEditText.getText().toString();
        String strPostTitle = m_vwPostTitleEditText.getText().toString();
        String strProfilePhotoAddress = "";
        String strUploadPhotoAddress = "";

        for (int i=0; i<strArray.length; i++) {
            Post newPost = new Post(dateToStamp(System.currentTimeMillis()), strPostTitle, m_strUserName, strProfilePhotoAddress, strPostContent, strUploadPhotoAddress, 0);
            addPost(newPost);
        }

        initAddPostListeners();
    }

    /**
     * Method used for encapsulating the logic necessary to properly initialize
     * a new post, add it to m_arrPostList, and display it on screen.
     *
     * @param post
     *            A string containing the text of the Post to add.
     */
    protected void addPost(Post post) {
        // TODO
        m_arrPostList.add(post);
        m_postAdapter.notifyDataSetChanged();
    }
    /**
     * Method used to encapsulate the code that initializes and sets the Event
     * Listeners which will respond to requests to "Add" a new Post to the
     * list.
     */
    protected void initAddPostListeners() {
        // TODO
        m_vwPostButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String strPostContent = m_vwPostEditText.getText().toString();
                String strPostTitle = m_vwPostTitleEditText.getText().toString();
                String strProfilePhotoAddress = "";
                String strUploadPhotoAddress = "";
                if(!strPostContent.isEmpty() && !strPostTitle.isEmpty()) {
                    addPost(new Post(dateToStamp(System.currentTimeMillis()), strPostTitle, m_strUserName, strProfilePhotoAddress, strPostContent, strUploadPhotoAddress, 0));
                    m_vwPostEditText.setText("");

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(m_vwPostEditText.getWindowToken(), 0);
                }
            }
        });

//        m_vwPostEditText.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                String postText = m_vwPostEditText.getText().toString();
//                if ((keyEvent.getAction()==KeyEvent.ACTION_UP)&&(i==KeyEvent.KEYCODE_ENTER)){
//                    addPost(new Post(postText.substring(0, postText.length()-1), m_strUserName));
//                    m_vwPostEditText.setText("");
//
//                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(m_vwPostEditText.getWindowToken(), 0);
//                }
//                return false;
//            }
//        });

    }
    protected String dateToStamp(long s) {
        String res;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(s);
            res = simpleDateFormat.format(date);
        } catch (Exception e) {
            return "";
        }
        return res;
    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
//        MenuItem menuItem1 = menu.add(menu.NONE, REMOVE_JOKE_MENUITEM, menu.NONE, R.string.remove_menuitem);
//        MenuItem menuItem2 = menu.add(menu.NONE, UPLOAD_JOKE_MENUITEM, menu.NONE, R.string.upload_menuitem);
//        menuItem1.setOnMenuItemClickListener(this);
//        menuItem2.setOnMenuItemClickListener(this);
//        super.onCreateContextMenu(menu, v, menuInfo);
//    }

//    @Override
//    public boolean onMenuItemClick(MenuItem item) {
//        Post post;
////        switch(item.getItemId()){
////            case REMOVE_JOKE_MENUITEM:
////                int selectedPosition = m_postAdapter.getSelectedPosition();
////                Log.v("Selected","selectedPosition");
////                post = m_arrSelectedPostList.get(selectedPosition);
////                m_arrSelectedPostList.remove(selectedPosition);
////                m_arrPostList.remove(post);
////                m_postAdapter.notifyDataSetChanged();
////                break;
////            case UPLOAD_JOKE_MENUITEM:
////                int selectedPosition1 = m_postAdapter.getSelectedPosition();
////                //Log.v("Selected",selectedPosition1);
////                post = m_arrSelectedPostList.get(selectedPosition1);
////                //Log.v("SelectedPost","post");
////                try {
////                    uploadPostToServer(post);
////                } catch (IOException e) {
////                    // TODO Auto-generated catch block
////                    e.printStackTrace();
////                }
////                break;
////            default:
////                break;
////        }
////        return true;
//    }

    protected void uploadPostToServer(Post post)  throws IOException {
        String urladdress = "http://pc066.comp.polyu.edu.hk/addOnePost.php?";
        String url = urladdress +"post_text=" + java.net.URLEncoder.encode(post.getM_strPostContent(),"UTF-8")
                + "&title=" + java.net.URLEncoder.encode(String.valueOf(post.getM_strPostTitle()),"UTF-8")
                + "&user=" + java.net.URLEncoder.encode(post.getM_strUserName(),"UTF-8")
                + "&time=" + java.net.URLEncoder.encode(post.getM_strTime(),"UTF-8")
                + "&profilePhoto=" + java.net.URLEncoder.encode(post.getM_strProfilePhotoAddress(),"UTF-8")
                + "&photo=" + java.net.URLEncoder.encode(post.getM_strPhotoAddress(),"UTF-8");
        Log.v("upload address", url);
        try {
            URL oburl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)oburl.openConnection();
            InputStream inputStream = new BufferedInputStream(conn.getInputStream());

            Scanner sc = new Scanner(inputStream);
            sc.useDelimiter("\n");
            if (sc.hasNext()) {
                String strOutput = sc.next();
                Log.v("InputStream",strOutput);
                if(strOutput.equalsIgnoreCase("1 record added")){
                    Toast.makeText(getBaseContext(), "Upload Succeeded!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getBaseContext(), "Upload Failed!", Toast.LENGTH_SHORT).show();
                }
            }
            sc.close();

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


//    @Override
//    public boolean onMenuItemClick(MenuItem menuItem) {
//        return false;
//    }

//    @Override
//    public boolean onMenuItemClick(MenuItem menuItem) {
//        return false;
//    }
}