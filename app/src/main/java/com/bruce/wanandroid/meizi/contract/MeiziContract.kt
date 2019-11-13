package com.bruce.wanandroid.meizi.contract

import com.bruce.wanandroid.base.mvp.IView
import com.bruce.wanandroid.meizi.bean.Meizi


interface MeiziContract {
    interface View : IView {
        fun onMeiziList(page: Int, list: List<Meizi>?)
    }

    interface Presenter {
        fun getMeiziList(page: Int, pageSize: Int)
    }
}