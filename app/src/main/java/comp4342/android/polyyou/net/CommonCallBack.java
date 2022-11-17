package comp4342.android.polyyou.net;

import android.util.Log;

import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import comp4342.android.polyyou.model.User;
import comp4342.android.polyyou.utils.GsonUtil;
import kotlin.reflect.KParameter;
import okhttp3.Call;

public abstract class CommonCallBack<T> extends StringCallback {

    private Type eType;

    public CommonCallBack() {
        Class<? extends CommonCallBack> aClass = getClass();
        Type genericSuperClass = aClass.getGenericSuperclass();
        if(genericSuperClass instanceof Class) {
            throw new RuntimeException("lack of generic");
        }
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperClass;
        eType = parameterizedType.getActualTypeArguments()[0];
    }
    @Override
    public void onError(Call call, Exception e, int id) {
        onError(e);
    }

    @Override
    public void onResponse(String response, int id) {
        try {
            JSONObject resp = new JSONObject(response);
            int resultCode = resp.getInt("resultCode");
            if(resultCode == 1) {
                String data = resp.getString("data");
                // Log.d("--------", data);
                if((!data.isEmpty()) && (data.charAt(0) == '{'))
                    onSuccess(GsonUtil.getGson().fromJson(data, eType));
                else
                    onSuccess(null);
            } else {
                onError(new RuntimeException(resp.getString("resultMsg")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            onError(e);
        }
    }

    public abstract void onError(Exception e);
    public abstract void onSuccess(T response);
}
