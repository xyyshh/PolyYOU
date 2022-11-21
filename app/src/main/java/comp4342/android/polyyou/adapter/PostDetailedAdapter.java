package comp4342.android.polyyou.adapter;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import comp4342.android.polyyou.R;
import comp4342.android.polyyou.activity.PostDetail;
import comp4342.android.polyyou.model.Post;

public class PostDetailedAdapter extends RecyclerView.Adapter<PostDetailedAdapter.ViewHolder> {

    private final Post post;
    private final Context context;

    public PostDetailedAdapter(Context context, Post post) {
        this.post = post;
        this.context = context;
    }

    @NonNull
    @Override
    public PostDetailedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_detailed, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
//        Glide.with(context)
//                .load(post.author.getHeadImage())
//                .placeholder(R.drawable.user_profile_photo)
//                .error(R.drawable.user_profile_photo)
//                .into(viewHolder.avatar);
        viewHolder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PostDetail.class);
            intent.putExtra("id", post.id);
            context.startActivity(intent);
        });
        viewHolder.content.setText(post.getPostContent());
        viewHolder.title.setText(post.getPostTitle());
        viewHolder.name.setText(post.author.getName());
        viewHolder.time.setText(post.getTime());
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.author_name)
        TextView name;

        @BindView(R.id.time)
        TextView time;

        @BindView(R.id.post_content)
        TextView content;

//        @BindView(R.id.comment_number)
//        TextView comment_number;

//        private PostBiz postBiz = new PostBiz();

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

