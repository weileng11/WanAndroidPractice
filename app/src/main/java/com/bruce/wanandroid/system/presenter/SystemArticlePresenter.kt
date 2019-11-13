package com.bruce.wanandroid.system.presenter

import com.bruce.wanandroid.apiservice.ApiService
import com.bruce.wanandroid.base.mvp.BasePresenter
import com.bruce.wanandroid.home.bean.ArticleResponse
import com.bruce.wanandroid.http.BaseObserver
import com.bruce.wanandroid.system.contract.SystemArticleContract


class SystemArticlePresenter : BasePresenter<SystemArticleContract.View>(), SystemArticleContract.Presenter {

    override fun getSystemArticles(page: Int, cid: Int) {
        addSubscribe(create(ApiService::class.java).getSystemArticles(page, cid),
            object : BaseObserver<ArticleResponse>() {
                override fun onSuccess(response: ArticleResponse?) {
                    getView()?.onSystemArticles(page, response?.datas)
                }
            })
    }
}