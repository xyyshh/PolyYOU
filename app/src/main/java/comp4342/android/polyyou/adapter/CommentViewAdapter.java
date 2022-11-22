package comp4342.android.polyyou.adapter;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.activity.Home;
import comp4342.android.polyyou.activity.PostDetail;
import comp4342.android.polyyou.model.Comment;
import comp4342.android.polyyou.model.Post;

import android.content.Context;

import androidx.annotation.NonNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import comp4342.android.polyyou.net.CommonCallBack;

import com.bumptech.glide.Glide;

public class CommentViewAdapter extends RecyclerView.Adapter<CommentViewAdapter.ViewHolder>{
    private final ArrayList<Comment> comments;
    private final Context context;

    public CommentViewAdapter(Context context, ArrayList<Comment> comments) {
        this.comments = comments;
        this.context = context;
    }


    @NonNull
    @Override
    public CommentViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        if()
//        View view = LayoutInflater.from(context).inflate(R.layout.post_collapse, viewGroup, false);
//        return new CommentViewAdapter.ViewHolder(view);
//        if(i==1){
            View commentView = View.inflate(context, R.layout.comment_item,null);
            return new CommentViewAdapter.ViewHolder(commentView);
//        }
//        else if (i==2){
//            View commentReplyView = View.inflate(context, R.layout.comment_reply,null);
//            return new CommentViewAdapter.ViewHolder(commentReplyView);
//        }
//        else{
//            View commentInputView = View.inflate(context, R.layout.comment_input,null);
//            return new CommentViewAdapter.ViewHolder(commentInputView);
//        }
    }

//    @Override
//    public int getItemViewType(int i) {
//        if(i==1){
//            return 3;//input box
//        }
//        Comment comment = comments.get(i-1);
//        if(comment.getCommentee()==null){
//            return 1;
//        }
//        else{
//            return 2;
//        }
//    }


    @Override
    public void onBindViewHolder(@NonNull CommentViewAdapter.ViewHolder viewHolder, int i) {
        Comment comment = comments.get(i-1);
        Glide.with(context)
                .load(comment.getProfilePhotoAddress())
                .into(viewHolder.avatar);
        viewHolder.itemView.setOnClickListener(v -> {
            viewHolder.comment_input.setHint("@"+comment.getCommenter().getName());
//            int position = viewHolder.getLayoutPosition();
            //DeliverdInfo
//            Intent intent = new Intent(context, PostDetail.class);
        });
        viewHolder.content.setText(comment.getCommentContent());
        viewHolder.time.setText(comment.getCommentTime());
//        viewHolder.commentee.setText(comment.getCommentee());
        viewHolder.commenter.setText(comment.getCommenter().getName());
//        viewHolder.avatar.setImageURI(Uri.parse(comment.getProfilePhotoAddress()));
//        viewHolder.button_submit.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                String comment_content = viewHolder.comment_input.getText().toString();
//
//        }});

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.commenter_profile_photo)
        ImageView avatar;

        @BindView(R.id.commenter_name)
        TextView commenter;

        @BindView(R.id.comment_time)
        TextView time;

        @BindView(R.id.comment_content)
        TextView content;

        @BindView(R.id.commentee_name)
            TextView commentee;

        @BindView(R.id.comment_input_box)
        EditText comment_input;

        @BindView(R.id.button_comment_submit)
        Button button_submit;



//        private PostBiz postBiz = new PostBiz();

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
