package com.bruce.sx.ui.rank

import com.bruce.sx.base.IBasePresenter
import com.bruce.sx.base.IBaseView
import com.bruce.sx.entity.RankEntity

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.rank
 * @description:
 * @date: 2020/3/30
 * @time:  16:09
 */
interface RankContract {
    interface View:IBaseView{
        fun showList(rankEntity: RankEntity)
    }

    interface Presenter<T>: IBasePresenter<View> {
        fun loadData(pageNum:Int)
    }
}