package com.bruce.sx.http;

import android.text.TextUtils;

import com.bruce.sx.utils.GsonUtil;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * @author: brcue
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.http
 * @description:带请求头案例参考
 * @date: 2020/3/27
 * @time: 9:49
 */
public class HeadInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

//        // 发送加密
//        Request request = chain.request();
//        RequestBody requestBody = request.body();
//        String logStr = request.header("log");//请求头
//        if (!TextUtils.isEmpty(logStr)) {
////            LogUtil.d("NetWorkTag", "执行：" + logStr);
//        }
//
//        String reqEncode_flag = request.header("encode_flag"); //请求头
//        if ("1".equals(reqEncode_flag)) {
//            try {
//                Buffer buffer = new Buffer();
//                requestBody.writeTo(buffer);
//                Charset charset = Charset.forName("UTF-8");
//                MediaType contentType = requestBody.contentType();
//                if (contentType != null) {
//                    charset = contentType.charset(charset);
//                }
//                String str = buffer.readString(charset);
////                String enStr = AESUtil.encrypt(str, key);
//                RequestBody enBody = MultipartBody.create(contentType, enStr);
//                request = request.newBuilder()
//                        .post(enBody)
//                        .build();
//            } catch (Exception e) {
////                    LogUtil.d(LogUtil.L, "Net Json2:" + e.toString());
//                e.printStackTrace();
//            }
//        }
//        if ("1".equals(request.header("need_sessionid"))) { //是否加密
//            String sessionId = SaveUtil.loadSessionId();
//            request = request.newBuilder()
//                    .addHeader("sessionId", sessionId)
//                    .build();
//        }
//
//        // 响应解密
//        Response originalResponse = chain.proceed(request);
//
//        ResponseBody body = originalResponse.body();
//        BufferedSource source = body.source();
//        source.request(Long.MAX_VALUE);
//        Buffer buffer = source.buffer();
//        Charset charset = Charset.defaultCharset();
//        MediaType contentType = body.contentType();
//        if (contentType != null) {
//            charset = contentType.charset(charset);
//        }
//        String str = buffer.clone().readString(charset);
//
////            LogUtil.d(LogUtil.L, "=====q==数据===q===：" + str.toString());
//
//        // 解压标记
//        String compress_flag = originalResponse.header("compress_flag");
//        String encode_flag = originalResponse.header("encode_flag");
//
//        //需要解压
//        if (!TextUtils.isEmpty(compress_flag)) {
//            if ("1".equals(encode_flag)) {
//                try {
//
//                    str = AESUtil.decrypt(str, key);
//                    str = uncompressWithGzip(str);
////                        LogUtil.d(LogUtil.L, "解密 1 ssss===== " + str);
//                    ResponseBody newBody = ResponseBody.create(contentType, str);
//                    originalResponse = originalResponse.newBuilder()
//                            .body(newBody)
//                            .build();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            } else {
//                str = uncompressWithGzip(str);
////                    LogUtil.d(LogUtil.L, "解密 2 ssss===== " + str);
//                ResponseBody newBody = ResponseBody.create(contentType, str);
//                originalResponse = originalResponse.newBuilder()
//                        .body(newBody)
//                        .build();
//            }
//        } else {
//            // 不需要解压
//            if ("1".equals(encode_flag)) {
//                try {
//                    str = AESUtil.decrypt(str, key);
////                        LogUtil.d(LogUtil.L, logStr + " 解密 3 ssss===== " + str);
//                    ResponseBody newBody = ResponseBody.create(contentType, str);
//                    originalResponse = originalResponse.newBuilder()
//                            .body(newBody)
//                            .build();
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//            RspBean bean = GsonUtil.getGson().fromJson(str, RspBean.class);
//            SaveUtil.saveToken(bean.getToken());
//        }
//        return originalResponse;
        return null;
    }
}
