package comp4342.android.polyyou.event;

public interface EventHandler {

    boolean handleMsg(int what, String msg, Object o);
}
