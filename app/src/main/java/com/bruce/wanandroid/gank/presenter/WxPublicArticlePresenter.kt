package com.bruce.wanandroid.gank.presenter

import com.bruce.wanandroid.apiservice.ApiService
import com.bruce.wanandroid.base.mvp.BasePresenter
import com.bruce.wanandroid.gank.contract.WxPublicArticleContract
import com.bruce.wanandroid.home.bean.ArticleResponse
import com.bruce.wanandroid.http.BaseObserver

class WxPublicArticlePresenter : BasePresenter<WxPublicArticleContract.View>(), WxPublicArticleContract.Presenter {
    override fun getWxPublicArticle(id: Int, page: Int) {
        addSubscribe(create(ApiService::class.java).getWxPublicArticle(id, page), object : BaseObserver<ArticleResponse>() {
            override fun onSuccess(data: ArticleResponse?) {
                getView()?.onWxPublicArticle(page, data?.datas)
            }
        })
    }

}