package com.example.dramaapp.utils;


import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    private static RetrofitUtils instance;

    public static String SERVER_URL = "http://www.mocky.io/";

    private RetrofitUtils() {
    }

    public static RetrofitUtils getInstance() {
        if (instance == null) {
            synchronized (RetrofitUtils.class) {
                if (instance == null) {
                    instance = new RetrofitUtils();
                }
            }
        }
        return instance;
    }

    public <S> S create(Class<S> service) {
        Retrofit retrofit = new Retrofit.Builder().client(getOkHttpClient()).baseUrl(SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create()) //使用Gson解析Json
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //使retrofit支援RxJava
                .build();
        return retrofit.create(service);
    }

    private OkHttpClient getOkHttpClient() {
        Builder builder = new Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        return builder.build();
    }
}