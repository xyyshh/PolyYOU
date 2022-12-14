package comp4342.android.polyyou.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import comp4342.android.polyyou.R;

/**
 * This class encapsulates the data pertaining to a Joke.
 */
public class Post{

    /** Contains the text of this post. */
    public String postTitle;
    public User author;
    public String postContent;
    public String attachedPhoto;
    public String time;
    public String tag_name;
    public String tag_id;
    public String id;
    private int m_commentNum;



    public Post() {
        this.postTitle = new String("");
        this.postContent = new String("");
        this.attachedPhoto = new String("");
//        this.photoAddress = new String("");
//        this.comments = new ArrayList<Comment>();

        this.m_commentNum = 0;
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

    public void setTag_id(String tagid){
        tag_id=tagid;
    }
    public String getTag_id(){return tag_id;}
    public String getPostTitle() {
        return postTitle;
    }
    public String getPostContent() {return postContent;}
    public String getUserName() {
        return author.getName();
    }
    public String getCommentNum() {
        return String.valueOf(this.m_commentNum);
//        return String.valueOf(comments.size());

        //return comments.size();
    }
    public String getTime(){return time;}
    public String getTag_name(){return tag_name;}
    public String getProfilePhotoAddress(){return author.getHeadImage(); }
    public String getPhotoAddress(){return attachedPhoto; }

    public String getId(){return id;}
    public User getAuthor() { return this.author; }
    public void setCommentNumber(int num){this.m_commentNum = num;}

    public void setAttachedPhoto(String photo) { this.attachedPhoto = photo; }
    public void setAuthor(User user){author = user;}
    public void setPostTitle(String str) {postTitle=str;}
    public void setPostContent(String str) {postContent =str;}

    @Override
    public String toString() {
        return "Post{" +
                "postTitle='" + postTitle + '\'' +
                ", author=" + author +
                ", postContent='" + postContent + '\'' +
                ", attachedPhoto='" + attachedPhoto + '\'' +
                ", time='" + time + '\'' +
//                ", comments=" + comments +
                ", tag_name='" + tag_name + '\'' +
                ", id=" + id +
                '}';
    }

    //    public void setM_commentNum(int num) {m_commentNum = num;}
//    public void setProfilePhotoAddress(String str){PhotoAddress = str; }
//    public void addComments(Comment comment){
//        comments.add(comment);}
    public void setTime(String str){
        time=str;
//        time = dateToStamp(System.currentTimeMillis());
    }
    public void setCurrentTime(){
        time = dateToStamp(System.currentTimeMillis());
    }
    public void setTag_name(String tag) {
        tag_name=tag;
    }

    public void setId(String id){
        this.id=id;
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
