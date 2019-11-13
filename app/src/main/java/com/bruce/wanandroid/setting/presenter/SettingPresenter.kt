package com.bruce.wanandroid.setting.presenter

import com.bruce.wanandroid.apiservice.ApiService
import com.bruce.wanandroid.base.mvp.BasePresenter
import com.bruce.wanandroid.http.BaseObserver
import com.bruce.wanandroid.setting.bean.LogoutResult
import com.bruce.wanandroid.setting.contract.SettingContract


class SettingPresenter : BasePresenter<SettingContract.View>(), SettingContract.Presenter {

    override fun logout() {
        addSubscribe(create(ApiService::class.java).logout(), object : BaseObserver<LogoutResult>() {
            override fun onSuccess(result: LogoutResult?) {
                getView()?.onLogoutResult()
            }
        })
    }
}