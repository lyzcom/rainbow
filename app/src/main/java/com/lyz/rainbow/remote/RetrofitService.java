package com.lyz.rainbow.remote;

import com.lyz.rainbow.data.bean.JHEssay;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ring on 17/7/17.
 */

public interface RetrofitService {

    @GET("todayOnhistory/queryEvent.php")
    Call<JHEssay> getEssay(@Query("key") String key, @Query("date") String date);

}
