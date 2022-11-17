package comp4342.android.polyyou.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import comp4342.android.polyyou.R;

/**
 * This class encapsulates the data pertaining to a Joke.
 */
public class Post implements ListItemModel {

    /** Contains the text of this post. */
    public String postTitle;
    public User author;
    public String postContent;
//    public String m_strProfilePhotoAddress;
    public String attachedPhoto;
    public String time;
    public ArrayList<Comment> comments;

//    private int m_commentNum;



//    public Post() {
//        this.m_strPostTitle = new String("");
//        this.m_strPostContent = new String("");
//        this.m_strProfilePhotoAddress = new String("");
//        this.m_strPhotoAddress = new String("");
//        this.m_comments = new ArrayList<Comment>();
////        this.m_commentNum = 0;
//    }


//    public Post(String strTime, String strPostTitle, String strUserName, String strProfilePhotoAddress, String strPostContent, String strPhotoAddress) {
//        m_strPostTitle = strPostTitle;
//        m_strUserName = strUserName;
//        m_strPostContent = strPostContent;
////        m_commentNum = commentNum;
//        m_strProfilePhotoAddress = strProfilePhotoAddress;
//        m_strPhotoAddress = strPhotoAddress;
//        setM_strTime(strTime);
//
//    }


    public String getPostTitle() {
        return postTitle;
    }
    public String getPostContent() {return postContent;}
    public String getUserName() {
        return author.getName();
    }
    public int getCommentNum() {
        return comments.size();
    }
    public String getTime(){return time;}
    public String getProfilePhotoAddress(){return author.getHeadImage(); }
    public String getPhotoAddress(){return attachedPhoto; }
    public ArrayList<Comment> getComments(){return comments;}

//    public void setM_strPostTitle(String str) {m_strPostTitle=str;}
//    public void setM_strPostContent(String str) {m_strPostContent =str;}
////    public void setM_commentNum(int num) {m_commentNum = num;}
//    public void setM_strProfilePhotoAddress(String str){m_strProfilePhotoAddress = str; }
//    public void addComments(Comment comment){
//        m_comments.add(comment);
//    }
//    public void setM_strTime(String str){
////        m_strTime = dateToStamp(System.currentTimeMillis());
//        m_strTime = str;
//    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_post;
    }

//    public String dateToStamp(long s) {
//        String res;
//        try {
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date date = new Date(s);
//            res = simpleDateFormat.format(date);
//        } catch (Exception e) {
//            return "";
//        }
//        return res;
//    }

//    @Override
//    public String toString() {
//        return getJoke();
//    }

}
