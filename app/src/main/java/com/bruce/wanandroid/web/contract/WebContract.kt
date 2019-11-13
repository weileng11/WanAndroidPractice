package com.bruce.wanandroid.web.contract

import com.bruce.wanandroid.base.mvp.IView
import com.bruce.wanandroid.web.bean.AddFavoriteResponse


interface WebContract {
    interface View : IView {
        fun onAddFavorited(addFavoriteResponse: AddFavoriteResponse?)
    }

    interface Presenter {
        fun addFavorite(id: Int, title: String, author: String, link: String)
    }
}