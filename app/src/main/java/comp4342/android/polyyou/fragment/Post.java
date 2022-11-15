package comp4342.android.polyyou.fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class encapsulates the data pertaining to a Joke.
 */
public class Post {

    /** Contains the text of this joke. */
    private String m_strPostTitle;
    private String m_strUserName;
    private String m_strPostContent;
    private String m_strTime;


    private int m_commentNum;



    public Post() {
        this.m_strPostTitle = new String("");
        this.m_strUserName = new String ("");
        this.m_strPostContent = new String("");
        m_commentNum = 0;
    }


    public Post(String strPostTitle, String strUserName, String strPostContent, int commentNum) {
        m_strPostTitle = strPostTitle;
        m_strUserName = strUserName;
        m_strPostContent = strPostContent;
        m_commentNum = commentNum;
        setM_strTime();

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
        return m_commentNum;
    }
    public String getM_strTime(){return m_strTime;}


    public void setM_strPostTitle(String str) {m_strPostTitle=str;}
    public void setM_strPostContent(String str) {m_strPostContent =str;}
    public void setM_strUserName(String str) {m_strUserName = str;}
    public void setM_commentNum(int num) {m_commentNum = num;}
    public void setM_strTime(){
        m_strTime = dateToStamp(System.currentTimeMillis());
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
