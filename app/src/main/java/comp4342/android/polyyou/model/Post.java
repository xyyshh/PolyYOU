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
    public String tag_name;
    public int id;

//    private int m_commentNum;



    public Post() {
        this.postTitle = new String("");
        this.postContent = new String("");
        this.attachedPhoto = new String("");
//        this.photoAddress = new String("");
        this.comments = new ArrayList<Comment>();

//        this.m_commentNum = 0;
    }


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
    public String getCommentNum() {
        return String.valueOf(comments.size());

        //return comments.size();
    }
    public String getTime(){return time;}
    public String getTag_name(){return tag_name;}
    public String getProfilePhotoAddress(){return author.getHeadImage(); }
    public String getPhotoAddress(){return attachedPhoto; }
    public ArrayList<Comment> getComments(){return comments;}
    public int getId(){return id;}

    public void setAuthor(User user){author = user;}
    public void setPostTitle(String str) {postTitle=str;}
    public void setPostContent(String str) {postContent =str;}
//    public void setM_commentNum(int num) {m_commentNum = num;}
//    public void setProfilePhotoAddress(String str){PhotoAddress = str; }
    public void addComments(Comment comment){
        comments.add(comment);}
    public void setTime(String str){
        time=str;
//        time = dateToStamp(System.currentTimeMillis());
    }
    public void setCurrentTime(){
        time = dateToStamp(System.currentTimeMillis());
    }
    public void setTag_name(String tag){tag_name=tag;}

    public void setId(int i){id=i;}

    @Override
    public int getLayoutId() {
        return R.layout.post_collapse;
    }

    public String dateToStamp(long s) {
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
//    public String toString() {
//        return getJoke();
//    }

}
