package com.lyz.rainbow.model.impl;

import com.google.gson.Gson;
import com.lyz.rainbow.Constant;
import com.lyz.rainbow.data.bean.Essay;
import com.lyz.rainbow.model.EssayModel;
import com.lyz.rainbow.remote.RetrofitManager;
import com.lyz.rainbow.remote.RetrofitService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ring on 17/7/17.
 */

public class EssayModelImpl implements EssayModel {

    @Override
    public void loadEssay(final LoadEssayListener listener) {

        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        String today = (month + 1) + "/" + day;

        RetrofitManager.getInstance().create(RetrofitService.class).getEssay(Constant.JHKey, today).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String result = jsonObject.getString("result");

                    JSONArray jsonArray = new JSONArray(result);
                    List<Essay> essays = new ArrayList<>();

                    Gson gson = new Gson();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        essays.add(gson.fromJson(String.valueOf(jsonArray.getJSONObject(i)), Essay.class));
                    }
                    listener.onSuccess(essays);

                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.onFailure();
            }
        });
    }
}
