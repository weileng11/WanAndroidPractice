package com.bruce.wanandroid.search.contract

import com.bruce.wanandroid.base.mvp.IView
import com.bruce.wanandroid.search.bean.SearchResultResponse


interface SearchResultContract {
    interface View : IView {
        fun onSearchResult(page: Int, response: SearchResultResponse?)
    }

    interface Presenter {
        fun getSearchResult(page: Int, keyword: String?)
    }
}