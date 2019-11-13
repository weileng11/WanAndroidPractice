package com.bruce.wanandroid.search.presenter

import com.bruce.wanandroid.apiservice.ApiService
import com.bruce.wanandroid.base.mvp.BasePresenter
import com.bruce.wanandroid.http.BaseObserver
import com.bruce.wanandroid.search.bean.SearchResultResponse
import com.bruce.wanandroid.search.contract.SearchResultContract


class SearchResultPresenter : BasePresenter<SearchResultContract.View>(), SearchResultContract.Presenter {
    override fun getSearchResult(page: Int, keyword: String?) {
        if (keyword == null) {
            return
        }
        addSubscribe(
            create(ApiService::class.java).getSearchResult(page, keyword),
            object : BaseObserver<SearchResultResponse>() {
                override fun onSuccess(response: SearchResultResponse?) {
                    if (this@SearchResultPresenter.isViewAttached()) {
                        this@SearchResultPresenter.getView()?.onSearchResult(page, response)
                    }
                }
            })
    }


}