package com.bruce.wanandroid.base.mvp

import com.bruce.wanandroid.base.BaseResponse
import com.bruce.wanandroid.http.BaseObserver
import com.bruce.wanandroid.http.RetrofitClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.ref.WeakReference


/**
 * @author: BasePresenter
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroid.base.mvp
 * @description:
 * @date: 2019/11/12
 * @time:  15:41
 */
open class BasePresenter<V :IView> :IPresenter<V>{

    private lateinit var viewReference: WeakReference<V>
    private var disposable: CompositeDisposable = CompositeDisposable()

    fun <T> addSubscribe(observable: Observable<BaseResponse<T>>, baseObserver: BaseObserver<T>) {
        val observer = observable.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(baseObserver)
        disposable.add(observer)
    }

    fun unsubscribe() {
        disposable.dispose()
    }

    fun <D> create(clazz: Class<D>): D {
        return RetrofitClient.get().retrofit.create(clazz)
    }

    override fun attachView(view: V) {
        viewReference = WeakReference(view)
    }

    override fun detachView() {
        viewReference.clear()
    }

    override fun isViewAttached(): Boolean {
        return viewReference.get() != null
    }

    override fun getView(): V? {
        return viewReference.get()
    }
}