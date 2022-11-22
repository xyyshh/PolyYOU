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
                .url(Config.baseUrl + "posts/list-by-notification")
                .tag(this)
                .addParams("uid", CurrentUser.getUser().getId())
                .build()
                .execute(commonCallBack);
    }

    public void loadPostByPostId(String id, CommonCallBack<Data> commonCallBack){
        OkHttpUtils
                .get()
                .url(Config.baseUrl + "posts/list-by-post-id")
                .tag(this)
                .addParams("pid", id)
                .build()
                .execute(commonCallBack);
    }

    public void loadPostByUserId(String id, CommonCallBack<Data> commonCallBack){
        OkHttpUtils
                .get()
                .url(Config.baseUrl + "posts/list-by-owner")
                .tag(this)
                .addParams("uid", CurrentUser.getUser().getId())
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

    public void onDestroy() {
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
