package comp4342.android.polyyou.view;
import comp4342.android.polyyou.model.ListItemModel;

public interface ListItemView {

    void onBind(ListItemModel item);

    void onItemClick();
}
