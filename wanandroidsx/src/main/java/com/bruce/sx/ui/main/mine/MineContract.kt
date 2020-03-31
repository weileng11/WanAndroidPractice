package com.bruce.sx.ui.main.mine

import com.bruce.sx.base.IBasePresenter
import com.bruce.sx.base.IBaseView
import com.bruce.sx.entity.IntegralEntity


interface MineContract {
    interface View: IBaseView {
        /**
         * 显示积分和用户信息
         */
        fun showIntegral(e: IntegralEntity)
    }

    interface Presenter<T>: IBasePresenter<View> {
        fun loadIntegral()
    }
}