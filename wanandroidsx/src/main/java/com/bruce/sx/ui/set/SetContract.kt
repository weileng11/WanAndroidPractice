package com.bruce.sx.ui.set

import com.bruce.sx.base.IBasePresenter
import com.bruce.sx.base.IBaseView


interface SetContract {
    interface View: IBaseView {
        fun logoutSuccess()
    }

    interface Presenter<T>: IBasePresenter<View> {
        fun logout()
    }
}