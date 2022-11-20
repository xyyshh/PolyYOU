package comp4342.android.polyyou.utils;

import android.content.Context;

public class SPUtils {
    private static SPUtils instance;
    private Context mCtx;

    public String mFileName = "share_data";

    private SPUtils(Context context, String fileName) {
        mCtx = context;
        this.mFileName = fileName;
    }

    public static SPUtils init(Context context, String fileName) {
        if(instance == null) {
            synchronized (SPUtils.class) {
                if (instance == null) {
                    instance = new SPUtils(context, fileName);
                }
            }
        }
        return instance;
    }

    public static SPUtils getInstance() {
        if(instance == null) {
            throw new IllegalStateException("You should add parameters");
        }
        return instance;
    }

    public static SPUtils getInstance(Context context, String fileName) {
        instance.init(context, fileName);
        return instance;
    }
}
