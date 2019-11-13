package com.bruce.wanandroid.system.contract

import com.bruce.wanandroid.base.mvp.IView
import com.bruce.wanandroid.home.bean.Article


interface SystemArticleContract {

    interface View : IView {
        fun onSystemArticles(page: Int, list: List<Article>?)
    }

    interface Presenter {
        fun getSystemArticles(page: Int, cid: Int)
    }

}