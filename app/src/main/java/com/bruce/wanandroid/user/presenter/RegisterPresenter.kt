package com.bruce.wanandroid.user.presenter

import com.bruce.wanandroid.apiservice.ApiService
import com.bruce.wanandroid.base.mvp.BasePresenter
import com.bruce.wanandroid.http.BaseObserver
import com.bruce.wanandroid.user.bean.RegisterResponse
import com.bruce.wanandroid.user.contract.RegisterContract


class RegisterPresenter : BasePresenter<RegisterContract.View>(), RegisterContract.Presenter {

    override fun register(username: String, password: String, repassword: String) {
        addSubscribe(create(ApiService::class.java).register(username, password, repassword),
            object : BaseObserver<RegisterResponse>() {
                override fun onSuccess(data: RegisterResponse?) {
                    getView()?.onRegisterResult(data)
                }
            })
    }
}