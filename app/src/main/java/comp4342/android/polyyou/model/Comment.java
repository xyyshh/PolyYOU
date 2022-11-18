package comp4342.android.polyyou.model;

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

    public String getCommentee() {
        return commentee;
    }

    public void setCommentee(String commentee) {
        this.commentee = commentee;
    }

    public Comment(User commenter, String commentee, String commentTime, String commentContent) {
        this.commenter = commenter;
        this.commentee = commentee;
        this.commentTime = commentTime;
        this.commentContent = commentContent;
    }
}
