package comp4342.android.polyyou.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.model.Post;


public class PostList extends Activity{

//    protected LinearLayout mContainer;
    /** Contains the list Posts the Activity will present to the user. */
    protected ArrayList<Post> m_arrPostList = new ArrayList<Post>();

//    /**
//     * Contains the selected list of Posts the Activity will present to the user.
//     **/
//    protected ArrayList<Post> m_arrSelectedPostList;

    /**
     * Adapter used to bind an AdapterView to List of Posts.
     */
    protected PostListAdapter m_postAdapter;

    /** LinearLayout used for maintaining a list of Views that each display Posts. */
    protected ListView m_vwPostLayout;
    protected LinearLayout m_container;

//    /** EditText used for entering text for a new Post to be added to m_arrPostList. */
//    protected EditText m_vwPostEditText;
//
//    /** Button used for creating and adding a new Post to m_arrPostList using the
//     * text entered in m_vwPostEditText. */
//    protected Button m_vwPostButton;


//    /**
//     * Context-Menu MenuItem ID's
//     * IMPORTANT: You must use these when creating your MenuItems or the tests
//     * used to grade your submission will fail.
//     */
//    protected int SHOWALL = -1;
//    protected int m_nFilter;
//protected static final int REMOVE_JOKE_MENUITEM = Menu.FIRST;
//    protected static final int UPLOAD_JOKE_MENUITEM = Menu.FIRST+1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO
        initLayout();
//        m_postAdapter = new PostListAdapter(getBaseContext(),m_arrPostList);
//        m_vwPostLayout.setAdapter(m_postAdapter);
        setContentView(R.layout.activity_home);
        m_container = m_container.findViewById(R.id.postRecycleView);
//        Resources resources = getResources();
//
//        String[] strArray = resources.getStringArray(R.array.postList);
//        m_strAuthorName = resources.getString(R.string.author_name);
//
//        for (int i=0; i<strArray.length; i++) {
//            Post newPost = new Post(strArray[i], m_strAuthorName);
//            addPost(newPost);
//        }
//
//        initAddPostListeners();
        registerForContextMenu(m_vwPostLayout);

        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    /**
     * Method used to encapsulate the code that initializes and sets the Layout
     * for this Activity.
     */
    public void initLayout() {
        setContentView(R.layout.activity_home);
        m_vwPostLayout = (ListView) findViewById(R.id.postRecycleView);
//        m_vwPostEditText = (EditText) findViewById(R.id.newPostEditText);
//        m_vwPostButton = (Button) findViewById(R.id.addPostButton);
        m_arrPostList = new ArrayList<Post>();
    }

}
