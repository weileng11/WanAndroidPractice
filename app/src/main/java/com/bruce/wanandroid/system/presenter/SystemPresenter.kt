package com.bruce.wanandroid.system.presenter

import com.bruce.wanandroid.apiservice.ApiService
import com.bruce.wanandroid.base.mvp.BasePresenter
import com.bruce.wanandroid.http.BaseObserver
import com.bruce.wanandroid.system.bean.SystemCategory
import com.bruce.wanandroid.system.contract.SystemContract


class SystemPresenter : BasePresenter<SystemContract.View>(), SystemContract.Presenter {

    override fun getSystemCategory() {
        addSubscribe(create(ApiService::class.java).getSystem(), object : BaseObserver<List<SystemCategory>>() {
            override fun onSuccess(data: List<SystemCategory>?) {
                getView()?.onSystemCategory(data)
            }
        })
    }
}