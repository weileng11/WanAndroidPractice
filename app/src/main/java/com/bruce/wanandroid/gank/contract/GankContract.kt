package com.bruce.wanandroid.gank.contract

import com.bruce.wanandroid.base.mvp.IView
import com.bruce.wanandroid.gank.bean.GankToday
import com.bruce.wanandroid.gank.bean.WxPublic


interface GankContract {
    interface View : IView {
        fun onWxPublic(list: List<WxPublic>?)

        fun onGankToday(map: HashMap<String, List<GankToday>>?)
    }

    interface Presenter {
        // 公众号
        fun getWxPublic()

        // 最新一天的干货
        fun getGankToday()
    }
}