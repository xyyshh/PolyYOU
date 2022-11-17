package comp4342.android.polyyou.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import java.util.List;

import comp4342.android.polyyou.model.Post;

//public class PostListAdapter extends BaseAdapter implements AdapterView.OnItemLongClickListener {
public class PostListAdapter extends BaseAdapter{

    /**
     * The application Context in which this PostListAdapter is being used.
     */
    private Context m_context;

    /**
     * The dataset to which this PostListAdapter is bound.
     */
    private List<Post> m_postList;

//    /**
//     * The position in the dataset of the currently selected Post.
//     */
//    private int m_nSelectedPosition;

    /**
     * Parameterized constructor that takes in the application Context in which
     * it is being used and the Collection of Post objects to which it is bound.
     * m_nSelectedPosition will be initialized to Adapter.NO_SELECTION.
     *
     * @param context
     *            The application Context in which this PostListAdapter is being
     *            used.
     *
     * @param postList
     *            The Collection of Post objects to which this PostListAdapter
     *            is bound.
     */
    public PostListAdapter(Context context, List<Post> postList) {
        //TODO
        m_context = context;
        m_postList = postList;
//        m_nSelectedPosition = Adapter.NO_SELECTION;
    }

//    /**
//     * Accessor method for retrieving the position in the dataset of the
//     * currently selected Post.
//     *
//     * @return an integer representing the position in the dataset of the
//     *         currently selected Post.
//     */
//    public int getSelectedPosition() {
//        //TODO
//        return m_nSelectedPosition;
//    }

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
//        PostView postView= new PostView(m_context,post);
        PostView postView= new PostView(m_context, post);
        return postView;
    }
//
//    public boolean onItemLongClick(AdapterView parent,View view, int position, long id){
//
//        m_nSelectedPosition=position;
//        return false;
//    }
}
