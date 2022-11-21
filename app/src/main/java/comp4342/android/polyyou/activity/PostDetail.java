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

import java.util.List;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.adapter.CommentViewAdapter;
import comp4342.android.polyyou.adapter.PostDetailedAdapter;
import comp4342.android.polyyou.adapter.PostViewAdapter;
import comp4342.android.polyyou.biz.PostBiz;
import comp4342.android.polyyou.model.Comment;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        int id = getIntent().getIntExtra("id", 1);
//        HtmlRender.render("http://47.94.134.55:8080/answer/" + id + ".html", this);
//        answerService.getAnswer(id);
//        answerService.setAnswerCallback(this);

        post = new Post();
        getPostById(String.valueOf(id));

        initLayout();
        initEvent();
    }

    public void getPostById(String id) {
        postBiz.loadComment(String.valueOf(id), new CommonCallBack<Data>() {
            @Override
            public void onError(Exception e) {
                Log.e("get_comments", "get comments error");
                T.showToast(e.toString());
            }

            @Override
            public void onSuccess(Data response) {
                List<Comment> lst = response.toArrayListComment();
                for(Comment comment: lst) {
                    Log.d("load_comments", comment.toString());
                    post.addComments(comment);
                }

                initView();
            }
        });
    }

    public void initView() {
        mPostAdapter = new PostDetailedAdapter(this, post);
        //设置适配器adapter
        mpostView.setAdapter(mPostAdapter);
        mpostView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false));

        mCommentAdapter = new CommentViewAdapter(this, post.comments);
        //设置适配器adapter
        mcommentView.setAdapter(mCommentAdapter);
        mcommentView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false));
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
                Comment comment = new Comment(post.author, post.author.getName(), comment_content);
                startLoadingProgress();
                postBiz.addComment(post, comment, new CommonCallBack<Post>(){
                    @Override
                    public void onError(Exception e) {
                        stopLoadingProgress();
                        Log.d("add comment", e.getMessage());
                    }

                    @Override
                    public void onSuccess(Post response) {
                        stopLoadingProgress();
                        Log.d("add comment", response.toString());
                    }
                });
            }
        });
    }
    private void initData(){
        post=new Post();
        User author = new User("Yooki", "1234", "", "");
        author.setHeadImage("hihih");
        post.setAuthor(author);
        post.setPostTitle("Help! I need the help!");
        post.setPostContent("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        post.setTag_name("1");
        post.setCurrentTime();
        Comment comment = new Comment(author,null,"Cool!");
        Comment comment1 = new Comment(author,null, "It looks soo cool! It looks soo cool! It looks soo cool! It looks soo cool! It looks soo cool! It looks soo cool! It looks soo cool! It looks soo cool! It looks soo cool!");
        Comment comment2 = new Comment(author,"hihi", "It looks soo cool! It looks soo cool!It looks soo cool!It looks soo cool!It looks soo cool! It looks soo cool!It looks soo cool!It looks soo cool!");
        Comment comment3 = new Comment(author,null, "Cool!");
        post.addComments(comment);
        post.addComments(comment1);
//        post.addComments(comment2);
//        post.addComments(comment3);
//        post.addComments(comment2);
//        post.addComments(comment3);
//        post.addComments(comment2);
//        post.addComments(comment3);
//        post.addComments(comment2);
//        post.addComments(comment3);
    }
}