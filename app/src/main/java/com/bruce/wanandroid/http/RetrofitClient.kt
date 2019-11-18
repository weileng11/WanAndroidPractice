package com.bruce.wanandroid.http

import com.bruce.wanandroid.app.MainApp
import com.bruce.wanandroid.utils.logInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


//https://blog.csdn.net/yuzhiqiang_1993/article/details/87863589
//Kotlin中init代码块和构造方法以及伴生对象中代码的调用时机及执行顺序
class RetrofitClient {

    var okHttpClient: OkHttpClient
    var retrofit: Retrofit

    private constructor() {

        okHttpClient = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())
            .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
            .addInterceptor(logInterceptor())
            .cookieJar(MainApp.getInstance().getPersistentCookieJar()) //cookieJar
//            .addInterceptor()
            .build()
        retrofit = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com/")
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        private var instance: RetrofitClient? = null
            get() {
                if (field == null) {
                    field = RetrofitClient()
                }
                return field
            }

        @Synchronized
        fun get(): RetrofitClient {
            return instance!!
        }
    }

}