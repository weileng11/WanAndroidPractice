package com.bruce.wanandroid.user.contract

import com.bruce.wanandroid.base.mvp.IView
import com.bruce.wanandroid.user.bean.RegisterResponse


interface RegisterContract {

    interface View : IView {
        fun onRegisterResult(result: RegisterResponse?)
    }

    interface Presenter {
        fun register(username: String, password: String, repassword: String)
    }
}