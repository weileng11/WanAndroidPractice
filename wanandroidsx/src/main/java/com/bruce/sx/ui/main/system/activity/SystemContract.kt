package com.bruce.sx.ui.main.system.activity

import com.bruce.sx.base.IBasePresenter
import com.bruce.sx.base.IBaseView
import com.bruce.sx.entity.ArticleEntity

/**
 * des 体系对应的文章
 */
interface SystemContract {

    interface View: IBaseView {
        fun showList(list: MutableList<ArticleEntity.DatasBean>)
        fun collectSuccess()
        fun unCollectSuccess()
    }
    interface Presenter<T> : IBasePresenter<View> {
        fun loadData(pageNum:Int,cid:Int)
        fun collect(id:Int)
        fun unCollect(id:Int)
    }
}