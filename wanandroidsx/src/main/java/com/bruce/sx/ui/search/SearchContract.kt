package com.bruce.sx.ui.search

import com.bruce.sx.base.IBasePresenter
import com.bruce.sx.base.IBaseView
import com.bruce.sx.entity.ArticleEntity


/**
 * @author zs
 * @date 2020-03-15
 */
interface SearchContract {
    interface View: IBaseView {
        fun showList(list: MutableList<ArticleEntity.DatasBean>)
        fun collectSuccess()
        fun unCollectSuccess()
    }

    interface Presenter<T> : IBasePresenter<View> {
        fun search(pageNum:Int,key:String)
        fun collect(id:Int)
        fun unCollect(id:Int)
    }
}