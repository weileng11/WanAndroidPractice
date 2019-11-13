package com.bruce.wanandroid.system.contract

import com.bruce.wanandroid.base.mvp.IView
import com.bruce.wanandroid.system.bean.SystemCategory


interface SystemContract {

    interface View : IView {
        fun onSystemCategory(data: List<SystemCategory>?)
    }

    interface Presenter {
        /**
         * 获取系统分类
         */
        fun getSystemCategory()
    }

}