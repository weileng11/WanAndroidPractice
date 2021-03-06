package com.bruce.sx.ui.main.home

import com.bruce.sx.base.IBasePresenter
import com.bruce.sx.base.IBaseView
import com.bruce.sx.entity.ArticleEntity
import com.bruce.sx.entity.BannerEntity
import com.bruce.sx.http.BaseResponse
import io.reactivex.Observable

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.main.home
 * @description:
 * @date: 2020/3/26
 * @time:  17:22
 */
interface HomeContract {
    interface Model {
        fun loadData(pageNum: Int): Observable<BaseResponse<ArticleEntity>>
        fun loadBanner(): Observable<BaseResponse<MutableList<BannerEntity>>>
        fun collect(id: Int): Observable<BaseResponse<Any>>
        fun unCollect(id: Int): Observable<BaseResponse<Any>>
    }

    interface View : IBaseView {
        fun showList(list: MutableList<ArticleEntity.DatasBean>)
        fun showBanner(bannerList: MutableList<BannerEntity>)
        fun collectSuccess()
        fun unCollectSuccess()
    }

    interface Presenter<T> : IBasePresenter<View> {
        fun loadData(pageNum: Int)
        fun loadBanner()
        fun collect(id: Int)
        fun unCollect(id: Int)
    }
}