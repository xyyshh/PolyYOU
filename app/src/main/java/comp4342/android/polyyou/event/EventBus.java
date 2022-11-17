package comp4342.android.polyyou.event;

import android.os.Handler;
import android.os.Looper;
//import android.support.annotation.UiThread;

import java.util.HashSet;
import java.util.Set;

public class EventBus {

    private static EventBus instance;

    private Handler handler;

    private Set<EventHandler> eventHandlers;

    private EventBus() {
        handler = new Handler(Looper.getMainLooper());
        eventHandlers = new HashSet<>();
    }

    public static EventBus getInstance() {
        if (instance == null) {
            synchronized (EventBus.class) {
                if (instance == null)
                    instance = new EventBus();
            }
        }
        return instance;
    }

    public void register(EventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

//    @UiThread
//    public void sendMessage(int what, Object o, String msg) {
//        for (EventHandler handler : eventHandlers) {
//            if (handler.handleMsg(what, msg, o))
//                return;
//        }
//        throw new IllegalArgumentException("no handler found for msg: " + msg);
//    }

    public void onMainThread(Runnable runnable) {
        handler.post(runnable);
    }
}

