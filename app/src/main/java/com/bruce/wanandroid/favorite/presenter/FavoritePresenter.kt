package com.bruce.wanandroid.favorite.presenter

import com.bruce.wanandroid.apiservice.ApiService
import com.bruce.wanandroid.base.mvp.BasePresenter
import com.bruce.wanandroid.favorite.contract.FavoriteContract
import com.bruce.wanandroid.home.bean.ArticleResponse
import com.bruce.wanandroid.http.BaseObserver


class FavoritePresenter : BasePresenter<FavoriteContract.View>(), FavoriteContract.Presenter {

    override fun getArticleFavorites(page: Int) {
        addSubscribe(create(ApiService::class.java).getArticleFavorites(page), object : BaseObserver<ArticleResponse>() {
            override fun onSuccess(response: ArticleResponse?) {
                getView()?.onArticleFavorite(page, response)
            }

            override fun onError(e: Throwable) {
                super.onError(e)
            }
        })
    }
}