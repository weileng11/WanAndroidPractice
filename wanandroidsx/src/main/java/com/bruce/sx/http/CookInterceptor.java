package com.bruce.sx.http;

import com.bruce.sx.constants.Constants;
import com.bruce.sx.utils.PrefUtils;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.http
 * @description:cookies拦截器
 * @date: 2020/3/27
 * @time: 9:48
 */
public class CookInterceptor  implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> cookies = (HashSet<String>) PrefUtils.INSTANCE.getHashSet(Constants.COOKIE);
        if (cookies != null) {
            for (String cookie : cookies) {
                builder.addHeader("Cookie", cookie);
            }
        }
        return chain.proceed(builder.build());
    }
}
