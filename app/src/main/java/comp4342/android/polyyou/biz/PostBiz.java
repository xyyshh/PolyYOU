package comp4342.android.polyyou.biz;

import android.net.Uri;

import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;

import comp4342.android.polyyou.Config;
import comp4342.android.polyyou.model.Comment;
import comp4342.android.polyyou.model.CurrentUser;
import comp4342.android.polyyou.model.Data;
import comp4342.android.polyyou.model.Post;
import comp4342.android.polyyou.model.User;
import comp4342.android.polyyou.net.CommonCallBack;



public class PostBiz {
    public void loadPostByTag(String tag, CommonCallBack<Data> commonCallBack){
        OkHttpUtils
                .get()
                .url(Config.baseUrl + "posts/list-by-tag")
                .tag(this)
                .addParams("tid", tag)
                .build()
                .execute(commonCallBack);
    }

    public void loadPostByNotification(CommonCallBack<Data> commonCallBack){
        OkHttpUtils
                .get()
                .url(Config.baseUrl + "post")
                .tag(this)
                .addParams("postId", CurrentUser.getUser().getId())
                .build()
                .execute(commonCallBack);
    }

    public void loadPostByUserId(CommonCallBack<Data> commonCallBack){
        OkHttpUtils
                .post()
                .url(Config.baseUrl + "post")
                .tag(this)
                .addParams("postId", CurrentUser.getUser().getId())
                .build()
                .execute(commonCallBack);
    }

    public void addPost(Post post, File file, CommonCallBack<Post> commonCallBack) {
        if (file == null)
            OkHttpUtils
                .post()
                .url(Config.baseUrl + "posts/sent-post")
                .tag(this)
                .addParams("tag", post.getTag_id().toString())
                .addParams("ownerId", post.author.getId())
                .addParams("creteTime", post.time)
                .addParams("postTitle", post.getPostTitle())
                .addParams("postContent", post.postContent)
                .build()
                .execute(commonCallBack);
        else
            OkHttpUtils
                    .post()
                    .url(Config.baseUrl + "posts/sent-post")
                    .tag(this)
                    .addParams("tag", post.getTag_id().toString())
                    .addParams("ownerId", post.author.getId())
                    .addParams("creteTime", post.time)
                    .addParams("postTitle", post.getPostTitle())
                    .addParams("postContent", post.postContent)
                    .addParams("postImage", file.getName())
                    .addFile("image", file.getName(), file)
                    .build()
                    .execute(commonCallBack);
    }
    public void addComment(Post post, Comment comment, CommonCallBack<Post> commonCallBack){
        OkHttpUtils
                .post()
                .url(Config.baseUrl + "comments/")
                .tag(this)
                .addParams("postId", String.valueOf(post.getId()))
                .addParams("commentAuthor", comment.getCommenter().getName())
                .addParams("commentTime", comment.getCommentTime())
                .addParams("commentContent", comment.getCommentContent())
                .build()
                .execute(commonCallBack);
    }

    public void loadComment(String postId, CommonCallBack<Data> commonCallBack){
        OkHttpUtils
                .get()
                .url(Config.baseUrl + "comments/list")
                .tag(this)
                .addParams("postId", String.valueOf(postId))
                .build()
                .execute(commonCallBack);
    }

    public void onDestroy() {
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
