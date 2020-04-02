package com.bruce.sx.http;

import com.bruce.sx.base.WanAndroidApplication;
import com.bruce.sx.constants.ApiConstants;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
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
        //打印log
//        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
//        if(BuildConfig.DEBUG){
//            //显示日志
//            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        }else {
//            logInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
//        }

        //文件缓存位置
        File cacheFile = new File(WanAndroidApplication.Companion.getContext().getCacheDir(), "cache");
        //100Mb
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);

        //cookjar
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(),
                        new SharedPrefsCookiePersistor(WanAndroidApplication.Companion.getContext()));

        okHttpClient = new OkHttpClient.Builder()
//                .cookieJar(new CookiesManager()) cookiejar
//                .addInterceptor(myInterceptor) 拦截器
//                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory()) //身份验证
//                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
//                .addInterceptor(logInterceptor)
                   .addInterceptor(new LoggingInterceptor())
//                .addInterceptor(new Interceptor() {
//
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Request originalRequest = chain.request(); //Current Request
//                        Response response = chain.proceed(originalRequest);
//                        /** DEBUG STUFF */
//                        if (BuildConfig.DEBUG) {
//                            //I am logging the response body in debug mode. When I do this I consume the response (OKHttp only lets you do this once) so i have re-build a new one using the cached body
//                            String bodyString = response.body().string();
//                            System.out.println(String.format("Sending request %s with headers %s ", originalRequest.url(), originalRequest.headers()));
//                            System.out.println(String.format("Got response HTTP %s %s \n\n with body %s \n\n with headers %s ", response.code(), response.message(), bodyString, response.headers()));
//                            response = response.newBuilder().body(ResponseBody.create(response.body().contentType(), bodyString)).build();
//                        }
//                        return response;
//                    }
//                })
                .connectTimeout(DEFAULT_CONNECT_TIME, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_WRITE_TIME, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_READ_TIME, TimeUnit.SECONDS)
                .cookieJar(cookieJar)
                .cache(cache)
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
