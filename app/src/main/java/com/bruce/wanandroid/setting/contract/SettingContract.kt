package com.bruce.wanandroid.setting.contract

import com.bruce.wanandroid.base.mvp.IView


interface SettingContract {

    interface View : IView {
        fun onLogoutResult()
    }

    interface Presenter {
        fun logout()
    }
}