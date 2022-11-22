package comp4342.android.polyyou.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.adapter.CommentViewAdapter;
import comp4342.android.polyyou.adapter.PostDetailedAdapter;
import comp4342.android.polyyou.adapter.PostViewAdapter;
import comp4342.android.polyyou.biz.CommentBiz;
import comp4342.android.polyyou.biz.PostBiz;
import comp4342.android.polyyou.model.Comment;
import comp4342.android.polyyou.model.CurrentUser;
import comp4342.android.polyyou.model.Data;
import comp4342.android.polyyou.model.Post;
import comp4342.android.polyyou.model.User;
import comp4342.android.polyyou.net.CommonCallBack;
import comp4342.android.polyyou.utils.Str;
import comp4342.android.polyyou.utils.T;

public class PostDetail extends BaseActivity {
    private Button btn_back;
    private PostDetailedAdapter mPostAdapter;
    private Post post;
    private RecyclerView mpostView;
    private CommentViewAdapter mCommentAdapter;
    private RecyclerView mcommentView;
    private EditText comment_input;
    private Button btn_sendComment;
    private PostBiz postBiz = new PostBiz();
    private CommentBiz commentBiz = new CommentBiz();
    private ArrayList<Comment> commentArrayList = new ArrayList<Comment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        String id = String.valueOf(getIntent().getIntExtra("id", 1));
        Log.i("PostDetail", id);
//        HtmlRender.render("http://47.94.134.55:8080/answer/" + id + ".html", this);
//        answerService.getAnswer(id);
//        answerService.setAnswerCallback(this);

        postBiz.loadPostByPostId(id, new CommonCallBack<Data>() {
            @Override
            public void onError(Exception e) {
                Log.e("get_post_by_pid", "get post error");
            }

            @Override
            public void onSuccess(Data response) {
                ArrayList<Post> postArrayList= response.toArrayListPost();
                post = postArrayList.get(0);
                Log.i("PostDetail_getPostByPid", post.toString());
            }
        });
        getCommentsByPostId(id);

        initLayout();
        initEvent();
    }

    public void getCommentsByPostId(String id){
        commentBiz.loadCommentsByPid(id, new CommonCallBack<Data>() {
            @Override
            public void onError(Exception e) {
                Log.e("get_comments", "get comments error");
                T.showToast(e.toString());
            }

            @Override
            public void onSuccess(Data response) {
//                List<Comment> lst = response.toArrayListComment();
                commentArrayList = response.toArrayListComment();
//                for(Comment comment: lst) {
//                    Log.d("load_comments", comment.toString());
////                    post.addComments(comment);
//                }
                initView();
            }
        });
    }

    public void initView() {
        mPostAdapter = new PostDetailedAdapter(PostDetail.this, post);
        //设置适配器adapter
        mpostView.setAdapter(mPostAdapter);
        mpostView.setLayoutManager(new LinearLayoutManager(PostDetail.this,
                LinearLayoutManager.VERTICAL,false));

        if(commentArrayList==null){
            mCommentAdapter = new CommentViewAdapter(this, commentArrayList);
            //设置适配器adapter
            mcommentView.setAdapter(mCommentAdapter);
            mcommentView.setLayoutManager(new LinearLayoutManager(this,
                    LinearLayoutManager.VERTICAL,false));
        }
    }

    public void initLayout() {
        comment_input = findViewById(R.id.comment_input_box);
        mcommentView = findViewById(R.id.commentRecycleView);
        mpostView = findViewById(R.id.post_detailed_view);
        btn_back = findViewById(R.id.button_post_back);
        btn_sendComment = findViewById(R.id.button_comment_submit);
        comment_input = findViewById(R.id.comment_input_box);
    }

    private void initEvent() {
        T.init(PostDetail.this);
        btn_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                PostDetail.this.finish();
//                Intent intent = new Intent(PostDetail.this, Home.class);
//                startActivity(intent);
            }

        });

        btn_sendComment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String comment_content = comment_input.getText().toString();
                Comment comment = new Comment(CurrentUser.getUser(), comment_content);
                comment.setOwner(CurrentUser.getUser().getId());
                comment.setPostId(String.valueOf(post.getId()));
                comment.setCommentTime(dateToStamp(System.currentTimeMillis()));
                startLoadingProgress();
                commentBiz.addComment(comment, new CommonCallBack<Data>(){
                    @Override
                    public void onError(Exception e) {
                        stopLoadingProgress();
                        Log.e("PostDetail", e.getMessage());
                    }

                    @Override
                    public void onSuccess(Data response) {
                        stopLoadingProgress();
                        Log.d("PostDetail", response.toArrayListComment().get(0).toString());
                    }
                });
            }
        });
    }

    protected String dateToStamp(long s) {
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