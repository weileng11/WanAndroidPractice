package com.bruce.sx.ui.login

import com.bruce.sx.base.IBasePresenter
import com.bruce.sx.base.IBaseView

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.login
 * @description:
 * @date: 2020/3/31
 * @time:  15:02
 */
interface LoginContract {
    interface View : IBaseView {
        fun loginSuccess()
    }
    interface Presenter<T>:
        IBasePresenter<View> {
        fun login(username:String,password:String)
    }
}