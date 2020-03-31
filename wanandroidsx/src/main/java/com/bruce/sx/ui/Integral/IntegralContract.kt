package com.bruce.sx.ui.Integral

import com.bruce.sx.base.IBasePresenter
import com.bruce.sx.base.IBaseView
import com.bruce.sx.entity.IntegralRecordEntity

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.Integral
 * @description:
 * @date: 2020/3/31
 * @time:  15:13
 */
interface IntegralContract {
    interface View:IBaseView{
        fun showList(t: IntegralRecordEntity)

    }

    interface Presenter<T>: IBasePresenter<View> {
        fun loadData(pageNum:Int)

    }
}