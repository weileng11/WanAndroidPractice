package com.bruce.sx.ui.share

import com.bruce.sx.base.IBasePresenter
import com.bruce.sx.base.IBaseView

interface ShareContract {
    interface View: IBaseView {
        fun shareSuccess()
    }
    interface Presenter<T>: IBasePresenter<View> {
        fun share(title:String,link:String)
    }
}