package com.example.admin.datecard.http;

import com.example.admin.datecard.UserBean;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.callback.Callback;

import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by admin on 2017/2/8.
 */
public class FriendResponse extends Callback<List<UserBean>> {
    @Override
    public List<UserBean> parseNetworkResponse(Response response, int id) throws Exception {
        Type type = new TypeToken<List<UserBean>>() {
        }.getType();
        GsonBuilder builder = new GsonBuilder().setLenient().serializeNulls();
        Gson gson = builder.create();
        return gson.fromJson(response.body().string(),type);
    }

    @Override
    public void onError(Call call, Exception e, int id) {

    }

    @Override
    public void onResponse(List<UserBean> response, int id) {

    }
}
