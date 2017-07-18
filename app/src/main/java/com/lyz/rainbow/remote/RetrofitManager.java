package com.lyz.rainbow.remote;

import com.lyz.rainbow.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ring on 17/7/18.
 */

public class RetrofitManager {
    private static RetrofitManager manager;
    private Retrofit mRetrofit;

    private RetrofitManager() {
        initRetrofit();
    }

    public static synchronized RetrofitManager getInstance() {
        if (manager == null) {
            manager = new RetrofitManager();
        }
        return manager;
    }

    private void initRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);
        OkHttpClient client = builder.build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                //以实体类返回
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

    }

    public <T> T create(Class<T> t) {
        return mRetrofit.create(t);
    }
}
