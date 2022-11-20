package comp4342.android.polyyou.model;

public class Response {

    private String mresponse;

    Response(String data) {
        mresponse = data;
    }

    public String getResponse() {
        return mresponse;
    }

    @Override
    public String toString() {
        return mresponse;
    }
}
