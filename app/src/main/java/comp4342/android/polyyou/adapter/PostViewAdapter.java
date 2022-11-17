package comp4342.android.polyyou.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.model.Post;

public class PostViewAdapter extends RecyclerView.Adapter {

    private ArrayList<Post> mList;//数据源
    public static final int TYPE_TITLE = 0;
    public static final int TYPE_CONTENT = 1;
    public static final int TYPE_USERNAME = 2;
    public static final int TYPE_TIME = 3;
    public static final int TYPE_COMMENT_NUM = 4;
    public static final int TYPE_PROFILE_PHOTO = 5;



    public PostViewAdapter(ArrayList<Post> list) {
        mList = list;
    }

//    //创建ViewHolder并返回，后续item布局里控件都是从ViewHolder中取出
//    @Override
//    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        //将我们自定义的item布局R.layout.item_one转换为View
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.fragment_post, parent, false);
//        //将view传递给我们自定义的ViewHolder
//        MyHolder holder = new MyHolder(R.layout.fragment_post);
//
//        //返回这个MyHolder实体
//        return holder;
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        switch (viewType){
            case TYPE_TITLE:
                return new PostTitleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_post,null));
            case TYPE_CONTENT:
                return new PostContentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_post,null));
            case TYPE_USERNAME:
                return new UserNameViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_post,null));
            case TYPE_TIME:
                return new TimeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_post,null));
            case TYPE_COMMENT_NUM:
                return new CommentNumberViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_post,null));
            case TYPE_PROFILE_PHOTO:
                return new ProfilePhotoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_post,null));
        }
        return null;

    }

//    //通过方法提供的ViewHolder，将数据绑定到ViewHolder中
//    @Override
//    public void onBindViewHolder(MyHolder holder, int position) {
//        holder.postContentView.setText(mList.get(position).getM_strPostContent());
//        holder.postTitleView.setText(mList.get(position).getM_strPostTitle());
//        holder.profilePhotoView.setImageURI(Uri.parse(mList.get(position).getM_strProfilePhotoAddress()));
//        holder.commentNumberView.setText(mList.get(position).getM_commentNum());
//        holder.timeView.setText(mList.get(position).getM_strTime());
//        holder.userNameView.setText(mList.get(position).getM_strUserName());
//    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type){
            case TYPE_TITLE:
                break;
            case TYPE_CONTENT:
                break;
            case TYPE_USERNAME:
                break;
            case TYPE_TIME:
                break;
            case TYPE_COMMENT_NUM:
                break;
            case TYPE_PROFILE_PHOTO:
                break;
        }
    }


    //获取数据源总的条数
    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 自定义的ViewHolder
     */
    class PostContentViewHolder extends RecyclerView.ViewHolder {
        TextView postContentView;
        public PostContentViewHolder(View item) {
            super(item);
            postContentView = item.findViewById(R.id.post_content);
        }
    }

    class PostTitleViewHolder extends RecyclerView.ViewHolder {
        TextView postTitleView;
        public PostTitleViewHolder(View item) {
            super(item);
            postTitleView = item.findViewById(R.id.title);
        }
    }
    class ProfilePhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView profilePhotoView;
        public ProfilePhotoViewHolder(View item) {
            super(item);
            profilePhotoView = item.findViewById(R.id.author_profile_photo);
        }
    }
    class UserNameViewHolder extends RecyclerView.ViewHolder {
        TextView userNameView;
        public UserNameViewHolder(View item) {
            super(item);
            userNameView = item.findViewById(R.id.author_name);
        }
    }
    class TimeViewHolder extends RecyclerView.ViewHolder {
        TextView timeView;
        public TimeViewHolder(View item) {
            super(item);
            timeView = item.findViewById(R.id.time);
        }
    }
    class CommentNumberViewHolder extends RecyclerView.ViewHolder {
        TextView commentNumberView;
        public CommentNumberViewHolder(View item) {
            super(item);
            commentNumberView = item.findViewById(R.id.comment_number);
        }
    }
}
