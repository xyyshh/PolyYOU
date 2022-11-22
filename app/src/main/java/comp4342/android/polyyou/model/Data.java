package comp4342.android.polyyou.model;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

import comp4342.android.polyyou.biz.PostBiz;
import comp4342.android.polyyou.biz.UserBiz;
import comp4342.android.polyyou.net.CommonCallBack;

public class Data implements Serializable {
    private String data;
    private Comment comment;
    public Data(String s) {
        this.data = data;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Data{" +
                "data='" + data + '\'' +
                '}';
    }

    public ArrayList<Post> toArrayListPost() {
        ArrayList<Post> a = new ArrayList<Post>();
        int i = 5;
        int len = this.data.length();
        while(i < len) {
            StringBuilder s = new StringBuilder();
            while(i < len && data.charAt(i) != '(')
                i++;
            i++;
            while(i < len && data.charAt(i) != ')') {
                s.append(data.charAt(i));
                i++;
            }
            Post post = new Post();
            String[] post_content = null;
            post_content = s.toString().split(", ");
            for(String ss: post_content) {
                String[] pair = ss.split("=");
                if(pair.length == 2) {
                //    Log.d("post_content", pair[0] + " " + pair[1]);
                    convertToPost(pair[0], pair[1], post);
                }
            }
            if(post.getAuthor()!=null && post.getPostTitle()!=null)
                a.add(post);
        }
        return a;
    }

    private void convertToPost(String key, String value, Post post) {
        if(key.equals("id"))    post.setId(Integer.valueOf(value));
        else if(key.equals("postTitle")) post.setPostTitle(value);
        else if(key.equals("createTime"))   post.setTime(value);
        else if(key.equals("postContent"))  post.setPostContent(value);
        else if(key.equals("tag"))  post.setTag_id(Long.valueOf(value));
        else if(key.equals("postImage"))    post.setAttachedPhoto(value);
        else if(key.equals("ownerId"))  post.setAuthor(new User("xy", value, "12", "33"));
    }

    public ArrayList<Comment> toArrayListComment() {
        ArrayList<Comment> a = new ArrayList<Comment>();
        int i = 0;
        int len = this.data.length();
        while(i < len) {
            StringBuilder s = new StringBuilder();
            while(i < len && data.charAt(i) != '(')
                i++;
            i++;
            while(i < len && data.charAt(i) != ')') {
                s.append(data.charAt(i));
                i++;
            }
            comment = new Comment();
            String[] comment_content = s.toString().split(", ");
            for(String ss: comment_content) {
                String[] pair = ss.split("=");
                if(pair.length == 2) {
                    convertToComment(pair[0], pair[1]);
                }
            }
            if(comment.getCommentContent() != null && comment.getCommentTime() != null){
                a.add(comment);
                Log.d("get_cmt_with_post_id", comment.toString());
            }

        }
        return a;
    }

    private User user = null;

    private void convertToComment(String k, String v) {

        if(k.equals("id"))  comment.setId(v);
        else if(k.equals("postId")) comment.setPostId(v);
        else if(k.equals("commentUserId")) {
            UserBiz userBiz = new UserBiz();
            userBiz.getUserById(v, new CommonCallBack<User>() {
                @Override
                public void onError(Exception e) {
                    Log.e("get_user_by_id", "get user by id error");
                }

                @Override
                public void onSuccess(User response) {
                    Log.d("get_user_by_id_result", response.toString());
                    user = response;
                    comment.setCommenter(user);
                }
            });
        }
        else if(k.equals("content"))    comment.setCommentContent(v);
        else if(k.equals("createTime")) comment.setCommentTime(v);
//        else if(k.equals("ownerId")) comment.setCommentee(v);
    }
}
