package com.bruce.wanandroid.favorite.contract

import com.bruce.wanandroid.base.mvp.IView
import com.bruce.wanandroid.home.bean.ArticleResponse


interface FavoriteContract {

    interface View : IView {
        fun onArticleFavorite(page: Int, response: ArticleResponse?)
    }

    interface Presenter {
        fun getArticleFavorites(page: Int)
    }
}