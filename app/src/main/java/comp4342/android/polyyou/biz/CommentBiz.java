package comp4342.android.polyyou.biz;

import com.zhy.http.okhttp.OkHttpUtils;

import comp4342.android.polyyou.Config;
import comp4342.android.polyyou.model.Comment;
import comp4342.android.polyyou.model.Data;
import comp4342.android.polyyou.model.Post;
import comp4342.android.polyyou.net.CommonCallBack;

public class CommentBiz {

    public void addComment(Comment comment, CommonCallBack<Data> commonCallBack){
        OkHttpUtils
                .post()
                .url(Config.baseUrl + "comments/post-comment/")
                .tag(this)
                .addParams("postId", comment.getPostId())
                .addParams("ownerId", comment.getOwner())
                .addParams("createTime", comment.getCommentTime())
                .addParams("content", comment.getCommentContent())
                .build()
                .execute(commonCallBack);
    }

    public void loadCommentsByPid(String pid, CommonCallBack<Data> commonCallBack){
        OkHttpUtils
                .get()
                .url(Config.baseUrl + "comments/list-by-pid")
                .tag(this)
                .addParams("pid", pid)
                .build()
                .execute(commonCallBack);
    }
}
