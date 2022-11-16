package comp4342.android.polyyou.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import comp4342.android.polyyou.R;
import comp4342.android.polyyou.model.Post;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyHolder> {

    private ArrayList<Post> mList;//数据源

    public RecycleViewAdapter(ArrayList<Post> list) {
        mList = list;
    }

    //创建ViewHolder并返回，后续item布局里控件都是从ViewHolder中取出
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //将我们自定义的item布局R.layout.item_one转换为View
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_post, parent, false);
        //将view传递给我们自定义的ViewHolder
        MyHolder holder = new MyHolder(view);
        //返回这个MyHolder实体
        return holder;
    }

    //通过方法提供的ViewHolder，将数据绑定到ViewHolder中
    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.postContentView.setText(mList.get(position).toString());
    }

    //获取数据源总的条数
    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 自定义的ViewHolder
     */
    class MyHolder extends RecyclerView.ViewHolder {

        TextView postContentView;

        public MyHolder(View itemView) {
            super(itemView);
            postContentView = itemView.findViewById(R.id.post_content);
        }
    }
}
