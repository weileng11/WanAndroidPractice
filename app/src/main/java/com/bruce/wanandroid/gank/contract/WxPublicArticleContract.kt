package com.bruce.wanandroid.gank.contract

import com.bruce.wanandroid.base.mvp.IView
import com.bruce.wanandroid.home.bean.Article


interface WxPublicArticleContract {

    interface View : IView {
        fun onWxPublicArticle(page: Int, list: List<Article>?)
    }

    interface Presenter {
        // 公众号
        fun getWxPublicArticle(id: Int, page: Int)
    }
}