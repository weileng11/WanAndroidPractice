package com.bruce.wanandroid.search.adapter

import androidx.annotation.LayoutRes
import com.bruce.wanandroid.search.bean.SearchHistory
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class SearchHistoryAdapter(@LayoutRes val layoutResId: Int) :
    BaseQuickAdapter<SearchHistory, BaseViewHolder>(layoutResId) {

    override fun convert(helper: BaseViewHolder?, item: SearchHistory?) {
//        helper.setText()
    }
}