package com.bruce.sx.http;

import com.google.gson.JsonParseException;
import com.orhanobut.logger.Logger;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: brcue
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.http
 * @description: 网络请求调用类
 * @date: 2020/3/27
 * @time: 9:54
 */
public class HttpManager {

    public static void doHttpRequest(final Observable observable, final HttpCallBack callBack) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseResponse>() {
                    @Override
                    public void accept(BaseResponse listRspBean) throws Exception {
                        Logger.d("INFO", "网络返回"+listRspBean.toString());
                        callBack.success(listRspBean.getData());
//                        listRspBean.getErrorCode();
//                        listRspBean.getErrorMsg();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
//                        Throwable throwable1 = new Exception("网络异常,请检查网络");
//                        callBack.fail(throwable1);

                        ExceptionHandle.handleException(throwable);
//                        errorException(throwable,callBack);
                    }
                });
    }

    public static void errorException(Throwable throwable,HttpCallBack callBack) {
        String error;
        if (throwable instanceof UnknownHostException) {
            error= "网络异常";
        }else if(throwable instanceof JSONException || throwable instanceof JsonParseException){
            error= "数据异常";
        }else if(throwable instanceof SocketTimeoutException ){
            error= "连接超时";
        }else if(throwable instanceof ConnectException ){
            error= "连接错误";
        }else{
            error=throwable.getMessage();
        }
        callBack.error(error);
    }

}
