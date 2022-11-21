package comp4342.android.polyyou.biz;

import com.zhy.http.okhttp.OkHttpUtils;

import comp4342.android.polyyou.Config;
import comp4342.android.polyyou.model.Comment;
import comp4342.android.polyyou.model.CurrentUser;
import comp4342.android.polyyou.model.Data;
import comp4342.android.polyyou.model.Post;
import comp4342.android.polyyou.model.User;
import comp4342.android.polyyou.net.CommonCallBack;



public class PostBiz {
    public void loadPost(String postType, CommonCallBack<Data> commonCallBack){
        OkHttpUtils
                .get()
                .url(Config.baseUrl + "posts/list")
                .tag(this)
                .build()
                .execute(commonCallBack);
    }

    public void loadPostbyNotification(User currentUser, CommonCallBack<Post> commonCallBack){
        OkHttpUtils
                .post()
                .url(Config.baseUrl + "post")
                .tag(this)
                .addParams("postId", currentUser.getId())
                .build()
                .execute(commonCallBack);
    }

    public void addpost(Post post, CommonCallBack<Post> commonCallBack) {
        OkHttpUtils
                .post()
                .url(Config.baseUrl + "post")
                .tag(this)
                .addParams("tag", post.getTag_id().toString())
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

    public void loadComment(Post post, CommonCallBack<Data> commonCallBack){
        OkHttpUtils
                .get()
                .url(Config.baseUrl + "comments/list")
                .tag(this)
                .addParams("postId", String.valueOf(post.getId()))
                .build()
                .execute(commonCallBack);
    }
    public void onDestroy() {
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
