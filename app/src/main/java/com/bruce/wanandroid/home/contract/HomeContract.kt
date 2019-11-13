package com.bruce.wanandroid.project.contract

import com.bruce.wanandroid.base.mvp.IView
import com.bruce.wanandroid.home.bean.Article
import com.bruce.wanandroid.home.bean.Banner


interface HomeContract {
    interface View : IView {

        fun onBanner(list: List<Banner>?)

        fun onArticles(page: Int, list: List<Article>?)
    }

    interface Presenter {

        fun getBanner()

        fun getArticles(page: Int)

    }

}