package com.bruce.sx.http.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Author: Sbingo666
 * Date:   2019/4/3
 */
class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader("Content-type", "application/json; charset=utf-8")
        return chain.proceed(builder.build())
    }
}