package comp4342.android.polyyou.biz;

import com.zhy.http.okhttp.OkHttpUtils;

import comp4342.android.polyyou.Config;
import comp4342.android.polyyou.model.Comment;
import comp4342.android.polyyou.model.CurrentUser;
import comp4342.android.polyyou.model.Post;
import comp4342.android.polyyou.net.CommonCallBack;



public class PostBiz {
    public void loadPost(String postType, CommonCallBack<Post> commonCallBack){
        OkHttpUtils
                .post()
                .url(Config.baseUrl + "post")
                .tag(this)
                .addParams("postType", postType)
                .build()
                .execute(commonCallBack);
    }

    public void loadPostbyNotification(CurrentUser currentUser, CommonCallBack<Post> commonCallBack){
        OkHttpUtils
                .post()
                .url(Config.baseUrl + "post")
                .tag(this)
                .addParams("postId", currentUser.getUser().getId())
                .build()
                .execute(commonCallBack);
    }

    public void addpost(Post post, CommonCallBack<Post> commonCallBack) {
        OkHttpUtils
                .post()
                .url(Config.baseUrl + "post")
                .tag(this)
                .addParams("tag_name", post.getTag_name())
                .addParams("authorName", post.author.getId())
                .addParams("time", post.time)
                .addParams("title", post.getPostTitle())
                .addParams("content", post.postContent)
                .build()
                .execute(commonCallBack);
    }
    public void addComment(Post post, Comment comment, CommonCallBack<Post> commonCallBack){
        OkHttpUtils
                .post()
                .url(Config.baseUrl + "comment")
                .tag(this)
                .addParams("postId", String.valueOf(post.getId()))
                .addParams("commentAuthor", comment.getCommenter().getName())
                .addParams("commentTime", comment.getCommentTime())
                .addParams("commentContent", comment.getCommentContent())
                .build()
                .execute(commonCallBack);
    }

    public void loadComment(Post post, CommonCallBack<Post> commonCallBack){
        OkHttpUtils
                .post()
                .url(Config.baseUrl + "comment")
                .tag(this)
                .addParams("postId", String.valueOf(post.getId()))
                .build()
                .execute(commonCallBack);
    }
    public void onDestroy() {
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
