package comp4342.android.polyyou.utils;
import com.google.gson.Gson;

public class GsonUtil {
    public static Gson eGson = new Gson();

    public static Gson getGson() { return eGson; }
}
