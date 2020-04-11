package com.bruce.sx.http

import android.util.Log
import com.bruce.sx.BuildConfig
import com.bruce.sx.base.WanAndroidApplication
import com.bruce.sx.constants.ApiConstants
import com.bruce.sx.http.interceptor.LoggingInterceptorLx
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.orhanobut.logger.Logger

import java.io.File
import java.util.concurrent.TimeUnit

import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.http
 * @description:Retrofit网络请求管理类
 * @date: 2020/3/27
 * @time: 9:37
 */
class RetrofitServiceManager3 private constructor() {

    /**
     * 设置使用okhttp网络请求
     */
    private val okHttpClient: OkHttpClient
    private val retrofit: Retrofit

    private class MyLog : LoggingInterceptorLx.Logger {
        override fun log(message: String) {
//            Log.i("_",message)
            Logger.d(message)
        }
    }

    init {
        //打印log
        //        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        //        if(BuildConfig.DEBUG){
        //            //显示日志
        //            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //        }else {
        //            logInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        //        }

        val loggingInterceptor = LoggingInterceptorLx(MyLog())
//        loggingInterceptor.level =
//            if (BuildConfig.DEBUG) LoggingInterceptorLx.Level.BODY else LoggingInterceptorLx.Level.NONE
        if (BuildConfig.DEBUG)  loggingInterceptor.setLevel(LoggingInterceptorLx.Level.BODY)
        else  loggingInterceptor.setLevel(LoggingInterceptorLx.Level.NONE)

//        val loggingInterceptor = HttpLoggingInterceptor()
//            if (BuildConfig.DEBUG)  headInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)  else  headInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE)

        //文件缓存位置
        val cacheFile = File(WanAndroidApplication.context!!.cacheDir, "cache")
        //100Mb
        val cache = Cache(cacheFile, (1024 * 1024 * 100).toLong())

        //cookjar
        val cookieJar = PersistentCookieJar(
            SetCookieCache(),
            SharedPrefsCookiePersistor(WanAndroidApplication.context!!)
        )

        okHttpClient = OkHttpClient.Builder()
            //                .cookieJar(new CookiesManager()) cookiejar
            //                .addInterceptor(myInterceptor) 拦截器
            //                .sslSocketFactory(SSLSocketClient.getSSLSocketFactory()) //身份验证
            //                .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
            //                .addInterceptor(logInterceptor)
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
            .connectTimeout(DEFAULT_CONNECT_TIME.toLong(), TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_WRITE_TIME.toLong(), TimeUnit.SECONDS)
            .readTimeout(DEFAULT_READ_TIME.toLong(), TimeUnit.SECONDS)
            .cookieJar(cookieJar)
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .build()

        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(ApiConstants.BASE_URL)
            //添加转化库，默认是Gson
            .addConverterFactory(GsonConverterFactory.create())
            //添加回调库，采用RxJava
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }

    companion object {
        /**
         * 连接超时时间
         */
        private val DEFAULT_CONNECT_TIME = 10
        /**
         * 设置写操作超时时间
         */
        private val DEFAULT_WRITE_TIME = 30
        /**
         * 设置读操作超时时间
         */
        private val DEFAULT_READ_TIME = 30
        /**
         * 获取RetrofitServiceManager
         */
        val instance: RetrofitServiceManager3

        init {
            instance = RetrofitServiceManager3()
        }

        fun api(): ApiService {
            return RetrofitServiceManager3.instance.create(ApiService::class.java)
        }
    }

}
