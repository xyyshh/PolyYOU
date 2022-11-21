package comp4342.android.polyyou.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {
    private User commenter;
    private Post commentPost;
    private String commentee;
    private String commentTime;
    private String commentContent;

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public User getCommenter() {
        return commenter;
    }

    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }
    public String getProfilePhotoAddress(){return commenter.getHeadImage(); }

    public String getCommentee() {
        return commentee;
    }

    public void setCommentee(String commentee) {
        this.commentee = commentee;
    }

    public Comment(User commenter, String commentee, String commentContent) {
        this.commenter = commenter;
        this.commentee = commentee;
        this.commentTime = dateToStamp(System.currentTimeMillis());
        this.commentContent = commentContent;
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
}
