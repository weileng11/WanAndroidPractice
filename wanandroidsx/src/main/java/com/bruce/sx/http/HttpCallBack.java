package com.bruce.sx.http;

import io.reactivex.disposables.Disposable;

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.http
 * @description:回调类
 * * @date: 2020/3/27
 * @time: 10:00
 */
public interface HttpCallBack<T> {
    void success(T rspBean) throws Exception;
//    void fail(Throwable t);
    void error(String message);
    void disposable(Disposable d);
}
