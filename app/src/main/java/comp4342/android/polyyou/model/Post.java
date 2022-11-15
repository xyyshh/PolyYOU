package comp4342.android.polyyou.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class encapsulates the data pertaining to a Joke.
 */
public class Post {

    /** Contains the text of this joke. */
    private String m_strPostTitle;
    private String m_strUserName;
    private String m_strPostContent;
    private String m_strProfilePhotoAddress;
    private String m_strPhotoAddress;
    private String m_strTime;
    private ArrayList<Comment> m_comments;

//    private int m_commentNum;



    public Post() {
        this.m_strPostTitle = new String("");
        this.m_strUserName = new String ("");
        this.m_strPostContent = new String("");
        this.m_strProfilePhotoAddress = new String("");
        this.m_strPhotoAddress = new String("");
        this.m_comments = new ArrayList<Comment>();
//        this.m_commentNum = 0;
    }


    public Post(String strTime, String strPostTitle, String strUserName, String strProfilePhotoAddress, String strPostContent, String strPhotoAddress) {
        m_strPostTitle = strPostTitle;
        m_strUserName = strUserName;
        m_strPostContent = strPostContent;
//        m_commentNum = commentNum;
        m_strProfilePhotoAddress = strProfilePhotoAddress;
        m_strPhotoAddress = strPhotoAddress;
        setM_strTime(strTime);

    }


    public String getM_strPostTitle() {
        return m_strPostTitle;
    }
    public String getM_strPostContent() {
        return m_strPostContent;
    }
    public String getM_strUserName() {
        return m_strUserName;
    }
    public int getM_commentNum() {
        return m_comments.size();
    }
    public String getM_strTime(){return m_strTime;}
    public String getM_strProfilePhotoAddress(){return m_strProfilePhotoAddress; }
    public String getM_strPhotoAddress(){return m_strPhotoAddress; }
    public ArrayList<Comment> getComments(){return m_comments;}

    public void setM_strPostTitle(String str) {m_strPostTitle=str;}
    public void setM_strPostContent(String str) {m_strPostContent =str;}
    public void setM_strUserName(String str) {m_strUserName = str;}
//    public void setM_commentNum(int num) {m_commentNum = num;}
    public void setM_strProfilePhotoAddress(String str){m_strProfilePhotoAddress = str; }
    public void addComments(Comment comment){
        m_comments.add(comment);
    }
    public void setM_strTime(String str){
//        m_strTime = dateToStamp(System.currentTimeMillis());
        m_strTime = str;
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


    /**
     * Returns only the text of the joke. This method should mimic getJoke().
     *
     * @return	A string containing the text of the joke
     */
//    @Override
//    public String toString() {
//        return getJoke();
//    }

}
