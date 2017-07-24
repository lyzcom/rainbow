package com.lyz.rainbow.model.impl;

import com.lyz.rainbow.Constant;
import com.lyz.rainbow.data.bean.JHEssay;
import com.lyz.rainbow.model.EssayModel;
import com.lyz.rainbow.remote.RetrofitManager;
import com.lyz.rainbow.remote.RetrofitService;

import java.util.Calendar;

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

        RetrofitManager.getInstance().create(RetrofitService.class).getEssay(Constant.JHKey, today).enqueue(new Callback<JHEssay>() {
            @Override
            public void onResponse(Call<JHEssay> call, Response<JHEssay> response) {
                listener.onSuccess(response.body().getEssays());
            }

            @Override
            public void onFailure(Call<JHEssay> call, Throwable t) {
                listener.onFailure();
            }
        });
    }
}
