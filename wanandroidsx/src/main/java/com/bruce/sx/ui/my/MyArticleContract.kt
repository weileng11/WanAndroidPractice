package com.bruce.sx.ui.my

import com.bruce.sx.base.IBasePresenter
import com.bruce.sx.base.IBaseView
import com.bruce.sx.entity.MyArticleEntity

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.my
 * @description:
 * @date: 2020/3/31
 * @time:  14:54
 */
interface MyArticleContract {
    interface View:IBaseView{
        fun showList(t: MyArticleEntity)
        fun deleteSuccess()
    }
    interface Presenter<T>: IBasePresenter<View> {
        fun loadData(pageNum:Int)
        fun delete(id:Int)
    }
}