package comp4342.android.polyyou.biz;

import com.zhy.http.okhttp.OkHttpUtils;

import comp4342.android.polyyou.Config;
import comp4342.android.polyyou.model.Post;
import comp4342.android.polyyou.model.User;
import comp4342.android.polyyou.net.CommonCallBack;



public class AddPostBiz {
    public void addpost(Post post, CommonCallBack<Post> commonCallBack) {
        OkHttpUtils
                .post()
                .url(Config.baseUrl + "post")
                .tag(this)
                .addParams("authorName", post.author.getName())
                .addParams("time", post.time)
                .addParams("title", post.getPostTitle())
                .addParams("content", post.postContent)
                .build()
                .execute(commonCallBack);
    }

    public void onDestroy() {
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
