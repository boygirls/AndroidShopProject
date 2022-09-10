package com.fxc.application;



import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * 作用：整个软件
 */
public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        /**
         * 初始化OkhttpUtils
         */
        initOkhttpClient();
    }

    public static Context getContext() {
        return mContext;
    }

    private void initOkhttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
