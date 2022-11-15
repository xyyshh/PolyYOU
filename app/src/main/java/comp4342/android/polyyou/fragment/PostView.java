package comp4342.android.polyyou.fragment;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import comp4342.android.polyyou.R;

public class PostView extends LinearLayout{
    private Post post;
    private TextView post_title;
    private ImageView profile_photo;
    private TextView user_name;
    private TextView time;
    private TextView post_content;
    private ImageView comment_icon;
    private TextView comment_number;


    /**
     * Basic Constructor that takes only takes in an application Context.
     *
     * @param context
     *            The application Context in which this view is being added.
     *
     * @param post
     * 			  The Joke this view is responsible for displaying.
     */
    public PostView(Context context, Post post) {
        super(context);
        // TODO
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_home, this, true);

        post_title = (TextView) findViewById(R.id.title);
        user_name = (TextView) findViewById(R.id.user_name);
        profile_photo = (ImageView) findViewById(R.id.user_profile_photo);
        time = (TextView) findViewById(R.id.time);
        post_content = (TextView) findViewById(R.id.post_content);
        comment_icon = (ImageView) findViewById(R.id.comment_icon);
        comment_number = (TextView) findViewById(R.id.comment_number);


//        m_vwExpandButton.setOnClickListener(this);
//        m_vwLikeGroup.setOnCheckedChangeListener(this);

        //m_vwLikeButton.setTextColor(getResources().getColor(R.color.text));
        //	m_vwDislikeButton.setTextColor(getResources().getColor(R.color.text));
        setPost(post);
//        m_checked = false;

        collapsePostView();
    }

    public PostView(Context context) {
        super(context);
    }

    /**
     * Mutator method for changing the Joke object this View displays. This View
     * will be updated to display the correct contents of the new Joke.
     *
     * @param post
     *            The Joke object which this View will display.
     */
    public void setPost(Post post) {
        // TODO
        post = post;
        post_title.setText(post.getM_commentNum());

        post_title.setTextSize(23);
        //profile_photo
        user_name.setText(post.getM_strUserName());
        time.setText(post.getM_strTime());
        post_content.setText(post.getM_strPostContent());
//        comment_icon.setImageIcon(getResources().getDrawable(R.drawable.comment_icon));
        comment_number.setText(post.getM_commentNum());
        //m_vwJokeText.setTextColor(getResources().getColor(R.color.text));

    }

    /**
     * This method encapsulates the logic necessary to update this view so that
     * it displays itself in its "Expanded" form:
     * 	- Shows the complete text of the joke.
     *  - Brings the RadioGroup of rating Buttons back into view.
     */
    private void expandPostView() {
        // TODO
        post_content.setSingleLine(false);
//        m_vwLikeGroup.setVisibility(View.VISIBLE);
        post_content.setPadding(1,1,1,16);
        requestLayout();
    }

    /**
     * This method encapsulates the logic necessary to update this view so that
     * it displays itself in its "Collapsed" form:
     * 	- Shows only the first line of text of the joke.
     *  - If the joke is longer than one line, it appends an ellipsis to the end.
     *  - Removes the RadioGroup of rating Buttons from view.
     */
    private void collapsePostView() {
        // TODO
        post_content.setSingleLine(true);
        post_content.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
//        m_vwExpandButton.setText(PostView.COLLAPSE);
//        m_vwLikeGroup.setVisibility(View.GONE);
        requestLayout();
    }
//    @Override
//    public void onClick(View v) {
//        // TODO Auto-generated method stub
//        String viewState = m_vwExpandButton.getText().toString();
//        if (viewState.equals(Post.EXPAND))
//        {
////            m_checked = false;
//            collapsePostView();
//        }
//        else {
////            m_checked = true;
//            expandPostView();
//        }
//    }
//    @Override
//    public void setChecked(boolean checked) {
//        // TODO Auto-generated method stub
//        if (checked == true){
//            m_checked = true;
//            expandJokeView();
//        }
//        else{
//            m_checked = false;
//            collapseJokeView();
//        }
//    }
//
//    @Override
//    public boolean isChecked() {
//        // TODO Auto-generated method stub
//        return m_checked;
//    }
//
//    @Override
//    public void toggle() {
//        // TODO Auto-generated method stub
//        if (m_checked == true){
//            m_checked = false;
//            collapseJokeView();
//        }
//        else{
//            m_checked = true;
//            expandJokeView();
//        }
//    }
//    @Override
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        // TODO Auto-generated method stub
//        if( checkedId == m_vwLikeButton.getId())
//            m_joke.setRating(Joke.LIKE);
//        if( checkedId == m_vwDislikeButton.getId())
//            m_joke.setRating(Joke.DISLIKE);
//
//    }

}

