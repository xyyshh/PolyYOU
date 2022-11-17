package comp4342.android.polyyou.view;

import android.content.Context;
//import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import comp4342.android.polyyou.R;
import comp4342.android.polyyou.event.Event;
import comp4342.android.polyyou.event.EventBus;
import comp4342.android.polyyou.model.ListItemModel;
import comp4342.android.polyyou.model.Post;


public class PostView extends LinearLayout implements ListItemView {

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

    private Post post;

//    private static final String INFO_FORMATTER = "%d 赞同 · %d 评论 · %d 收藏";

    public PostView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onBind(ListItemModel item) {
        ButterKnife.bind(this);
        post = (Post) item;
        title.setText(post.postTitle);
        content.setText(post.postContent);
        name.setText(post.author.getName());
        time.setText(post.time);
        Glide.with(getContext())
                .load(post.author.getHeadImage())
                .placeholder(R.drawable.user_profile_photo)
                .error(R.drawable.user_profile_photo)
                .into(avatar);
    }

    @Override
    @OnClick
    public void onItemClick() {
        EventBus.getInstance().sendMessage(Event.Click.ON_POST_CLICK,
                post.author.getId(), "on post click");
    }
}
