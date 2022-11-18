package comp4342.android.polyyou.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.adapter.CommentViewAdapter;
import comp4342.android.polyyou.adapter.PostDetailedAdapter;
import comp4342.android.polyyou.adapter.PostViewAdapter;
import comp4342.android.polyyou.model.Comment;
import comp4342.android.polyyou.model.Post;
import comp4342.android.polyyou.model.User;
import comp4342.android.polyyou.net.CommonCallBack;
import comp4342.android.polyyou.utils.T;

public class PostDetail extends AppCompatActivity {
    private Button btn_back;
    private PostDetailedAdapter mPostAdapter;
    private Post post;
    private RecyclerView mpostView;
    private CommentViewAdapter mCommentAdapter;
    private RecyclerView mcommentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        int id = getIntent().getIntExtra("id", 1);
//        HtmlRender.render("http://47.94.134.55:8080/answer/" + id + ".html", this);
//        answerService.getAnswer(id);
//        answerService.setAnswerCallback(this);
        initData();
        mpostView = findViewById(R.id.post_detailed_view);
        mPostAdapter = new PostDetailedAdapter(this, post);
        //设置适配器adapter
        mpostView.setAdapter(mPostAdapter);
        mpostView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false));

        mcommentView = findViewById(R.id.commentRecycleView);
        mCommentAdapter = new CommentViewAdapter(this, post.comments);
        //设置适配器adapter
        mcommentView.setAdapter(mCommentAdapter);
        mcommentView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false));
        initLayout();
        initEvent();
    }

    public void initLayout() {
        btn_back = findViewById(R.id.button_post_back);
    }
    private void initEvent() {
        T.init(PostDetail.this);
        btn_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostDetail.this, Home.class);
                startActivity(intent);
            }

        });
    }
    private void initData(){
        post=new Post();
        User author = new User("Yooki", "1234");
        author.setHeadImage("hihih");
        post.setAuthor(author);
        post.setPostTitle("Help! I need the help!");
        post.setPostContent("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        post.setTag_name("1");
        post.setCurrentTime();
        Comment comment = new Comment(author,null,"2001-12-26 12:00:00", "Cool!");
        Comment comment1 = new Comment(author,null,"2001-12-26 12:00:00", "It looks soo cool! It looks soo cool! It looks soo cool! It looks soo cool! It looks soo cool! It looks soo cool! It looks soo cool! It looks soo cool! It looks soo cool!");
        Comment comment2 = new Comment(author,"hihi","2001-12-26 12:00:00", "It looks soo cool! It looks soo cool!It looks soo cool!It looks soo cool!It looks soo cool! It looks soo cool!It looks soo cool!It looks soo cool!");
        Comment comment3 = new Comment(author,null,"2001-12-26 12:00:00", "Cool!");
        post.addComments(comment);
        post.addComments(comment1);
        post.addComments(comment2);
        post.addComments(comment3);
        post.addComments(comment2);
        post.addComments(comment3);
        post.addComments(comment2);
        post.addComments(comment3);
        post.addComments(comment2);
        post.addComments(comment3);
    }
}