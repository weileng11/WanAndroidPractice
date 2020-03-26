package com.bruce.sx.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author: brcue
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.base
 * @description: 增加open，class可以被继承了
 * @date: 2020/3/26
 * @time:  11:32
 */
open class BasePresenter<V:IBaseView>(view:V):IBasePresenter<V>{

    protected var view:V?=view
    private var compositeDisposable :CompositeDisposable?=null

    init {
        compositeDisposable= CompositeDisposable()
    }

    override fun onCreate() {
    }

    override fun onStart() {
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onStop() {
    }

    override fun onDestroy() {
        compositeDisposable?.clear()
    }

    protected fun addSubscribe(disposable: Disposable){
        compositeDisposable?.add(disposable)
    }


}