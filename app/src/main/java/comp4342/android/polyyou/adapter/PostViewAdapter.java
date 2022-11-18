package comp4342.android.polyyou.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.activity.Home;
import comp4342.android.polyyou.activity.PostDetail;
import comp4342.android.polyyou.model.Post;

import android.content.Context;

import androidx.annotation.NonNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import comp4342.android.polyyou.view.PostView;

import com.bumptech.glide.Glide;

public class PostViewAdapter extends RecyclerView.Adapter<PostViewAdapter.ViewHolder> {

    private final ArrayList<Post> posts;
    private final Context context;

    public PostViewAdapter(Context context, ArrayList<Post> posts) {
        this.posts = posts;
        this.context = context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_collapse, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Post post = posts.get(i);
        Glide.with(context)
                .load(post.author.getHeadImage())
                .placeholder(R.drawable.user_profile_photo)
                .error(R.drawable.user_profile_photo)
                .into(viewHolder.avatar);
        viewHolder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PostDetail.class);
            intent.putExtra("id", post.id);
            context.startActivity(intent);
        });
        viewHolder.content.setText(post.getPostContent());
        viewHolder.title.setText(post.getPostTitle());
        viewHolder.name.setText(post.author.getName());
        viewHolder.time.setText(post.getTime());
        viewHolder.comment_number.setText(post.getCommentNum());

    }

    @Override
    public int getItemCount() {
        System.out.println("Post num is "+posts.size());
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.author_profile_photo)
        ImageView avatar;

        @BindView(R.id.author_name)
        TextView name;

        @BindView(R.id.time)
        TextView time;

        @BindView(R.id.post_content)
        TextView content;

        @BindView(R.id.comment_number)
        TextView comment_number;

//        private PostBiz postBiz = new PostBiz();

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }



    }
}
