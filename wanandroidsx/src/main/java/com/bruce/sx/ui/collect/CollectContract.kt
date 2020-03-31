package com.bruce.sx.ui.collect

import com.bruce.sx.base.IBasePresenter
import com.bruce.sx.base.IBaseView
import com.bruce.sx.entity.CollectEntity

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.collect
 * @description:
 * @date: 2020/3/31
 * @time:  15:28
 */
interface CollectContract {
    interface View:IBaseView{
        fun showList(list: MutableList<CollectEntity.DatasBean>)
        fun cancelCollectSuccess()
    }

    interface Presenter<T> : IBasePresenter<View> {
        fun loadData(page:Int)
        fun cancelCollect(id:Int)
    }
}