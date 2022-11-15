package comp4342.android.polyyou.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import java.util.List;

public class PostListAdapter extends BaseAdapter implements AdapterView.OnItemLongClickListener {

    /**
     * The application Context in which this JokeListAdapter is being used.
     */
    private Context m_context;

    /**
     * The dataset to which this JokeListAdapter is bound.
     */
    private List<Post> m_jokeList;

    /**
     * The position in the dataset of the currently selected Joke.
     */
    private int m_nSelectedPosition;

    /**
     * Parameterized constructor that takes in the application Context in which
     * it is being used and the Collection of Joke objects to which it is bound.
     * m_nSelectedPosition will be initialized to Adapter.NO_SELECTION.
     *
     * @param context
     *            The application Context in which this JokeListAdapter is being
     *            used.
     *
     * @param postList
     *            The Collection of Joke objects to which this JokeListAdapter
     *            is bound.
     */
    public Post(Context context, List<Post> postList) {
        //TODO
        m_context = context;
        m_jokeList = postList;
        m_nSelectedPosition = Adapter.NO_SELECTION;
    }

    /**
     * Accessor method for retrieving the position in the dataset of the
     * currently selected Joke.
     *
     * @return an integer representing the position in the dataset of the
     *         currently selected Joke.
     */
    public int getSelectedPosition() {
        //TODO
        return m_nSelectedPosition;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return m_jokeList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return m_jokeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Post post = m_jokeList.get(position);
        PostView postView= new PostView(m_context,post);
        return postView;
    }

    public boolean onItemLongClick(AdapterView parent,View view, int position, long id){

        m_nSelectedPosition=position;
        return false;
    }
}
