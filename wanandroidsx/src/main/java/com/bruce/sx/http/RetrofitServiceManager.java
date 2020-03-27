package com.bruce.sx.http;

import com.zs.wanandroid.constants.ApiConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.http
 * @description:Retrofit网络请求管理类
 * @date: 2020/3/27
 * @time: 9:37
 */
public class RetrofitServiceManager {
    /**
     * 连接超时时间
     */
    private static final int DEFAULT_CONNECT_TIME = 10;
    /**
     * 设置写操作超时时间
     */
    private static final int DEFAULT_WRITE_TIME = 30;
    /**
     * 设置读操作超时时间
     */
    private static final int DEFAULT_READ_TIME = 30;

    /**
     * 设置使用okhttp网络请求
     */
    private final OkHttpClient okHttpClient;
    private final Retrofit retrofit;
    private static final RetrofitServiceManager INSTANCE;

    static {
        INSTANCE = new RetrofitServiceManager();
    }

    private RetrofitServiceManager() {
        okHttpClient = new OkHttpClient.Builder()
//                .cookieJar(new CookiesManager()) cookiejar
//                .addInterceptor(myInterceptor) 拦截器
//                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory()) //身份验证
//                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
                .connectTimeout(DEFAULT_CONNECT_TIME, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIME, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIME, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(ApiConstants.BASE_URL)
                //添加转化库，默认是Gson
                .addConverterFactory(GsonConverterFactory.create())
                //添加回调库，采用RxJava
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     *  获取RetrofitServiceManager
     */
    public static RetrofitServiceManager getInstance() {
        return INSTANCE;
    }

    public static ApiService api() {
        return RetrofitServiceManager.getInstance().create(ApiService.class);
    }

    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }

}
