package com.bruce.wanandroid.user.contract

import com.bruce.wanandroid.base.mvp.IView
import com.bruce.wanandroid.db.bean.User


interface LoginContract {

    interface View : IView {
        fun onLoginResult(username: String, user: User?)
    }

    interface Presenter {
        fun login(username: String, password: String)
    }
}
