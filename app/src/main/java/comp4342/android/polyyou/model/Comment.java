package comp4342.android.polyyou.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Post getPost() {
        return commentPost;
    }

    public void setPost(Post post) {
        this.commentPost = post;
    }

    private String id;
    private User commenter;
    private Post commentPost;
    private String commentee;
    private String commentTime;
    private String commentContent;
    private String postId;

    public void setPostId(String postId) { this.postId = postId; }
    public String getPostId() { return this.postId; }

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

    public Comment() {}

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

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", commenter=" + commenter +
                ", commentPost=" + commentPost +
                ", commentee='" + commentee + '\'' +
                ", commentTime='" + commentTime + '\'' +
                ", commentContent='" + commentContent + '\'' +
                '}';
    }
}
