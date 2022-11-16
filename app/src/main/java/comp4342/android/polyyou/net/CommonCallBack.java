package comp4342.android.polyyou.net;

import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class CommonCallBack<T> extends StringCallback {

    @Override
    public void onError(Call call, Exception e, int id) {

    }

    @Override
    public void onResponse(String response, int id) {

    }
}
