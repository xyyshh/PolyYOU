package comp4342.android.polyyou.adapter;


import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import comp4342.android.polyyou.fragment.PostView;
import comp4342.android.polyyou.model.Post;

public class PostAdapter extends BaseAdapter implements AdapterView.OnItemLongClickListener {

    private Context m_context;
    private List<Post> m_postList;
    private int m_nSelectedPosition;

    public PostAdapter(Context context, List<Post> postList) {
        //TODO
        m_context = context;
        m_postList = postList;
        m_nSelectedPosition = Adapter.NO_SELECTION;
    }

    public int getSelectedPosition() {
        //TODO
        return m_nSelectedPosition;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return m_postList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return m_postList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Post post = m_postList.get(position);
        PostView postView = new PostView(m_context,post);
        return postView;
    }

    public boolean onItemLongClick(AdapterView parent,View view, int position, long id){
        m_nSelectedPosition=position;
        return false;
    }
}
