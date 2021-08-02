package com.cas.veritasapp.core.api;


import android.content.Context;

import com.cas.veritasapp.BuildConfig;
import com.cas.veritasapp.core.constant.AppConstant;
import com.cas.veritasapp.util.LogUtil;
import com.cas.veritasapp.util.PrefUtil;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by funmiayinde on 2019-10-10.
 */
public class RetrofitRequest {
    private static OkHttpClient.Builder client = new OkHttpClient.Builder();

    private static Retrofit retrofit;

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BuildConfig.HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

    private static OkHttpClient.Builder attachProps(OkHttpClient.Builder httpClient) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpClient
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor);
    }

    private static OkHttpClient.Builder attachJwtProps(Context context) {
        return client.addInterceptor(chain -> {
            Request request = chain.request();
            Request.Builder requestBuilder = request.newBuilder()
                    .header("x-api-key", BuildConfig.X_API_KEY);

            if (PrefUtil.getStringData(context, AppConstant.TOKEN) != null) {
                requestBuilder = requestBuilder.header("Authorization", PrefUtil.getStringData(context, AppConstant.TOKEN));
            }
            requestBuilder = requestBuilder.method(request.method(), request.body());
            Request request1 = requestBuilder.build();
            Response response = chain.proceed(request1);
            LogUtil.debug("CODE: ", String.valueOf(response.code()));
            LogUtil.debug("URL: ", String.valueOf(request1.url()));

            if (request1.body() != null) {
                LogUtil.debug("Body:", String.valueOf(response.body()));
            }
            if (PrefUtil.getStringData(context, AppConstant.TOKEN) != null && response.code() != 401) {
                // Do logout here
            }
            return response;
        });
    }

    public static <S> S createService(Class<S> serviceClazz, Context context) {
        client = attachJwtProps(context);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = client
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();
        return getRetrofit(okHttpClient).create(serviceClazz);
    }

    public static Retrofit getRetrofit(OkHttpClient okHttpClient) {
        if (retrofit == null) {
            retrofit = builder.client(okHttpClient).build();
        }
        return retrofit;
    }

}
