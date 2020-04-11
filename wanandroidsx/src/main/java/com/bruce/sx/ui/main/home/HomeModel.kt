package com.bruce.sx.ui.main.home

import com.bruce.sx.entity.ArticleEntity
import com.bruce.sx.entity.BannerEntity
import com.bruce.sx.http.BaseResponse
import com.bruce.sx.http.RetrofitServiceManager3
import io.reactivex.Observable

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.main.home
 * @description:
 * @date: 2020/3/31
 * @time:  11:48
 */
class HomeModel :HomeContract.Model{
    override fun loadData(pageNum: Int): Observable<BaseResponse<ArticleEntity>> {
        return RetrofitServiceManager3.api().getHomeList(pageNum)
    }

    override fun loadBanner(): Observable<BaseResponse<MutableList<BannerEntity>>> {
        return RetrofitServiceManager3.api().getBanner()
    }

    override fun collect(id: Int): Observable<BaseResponse<Any>> {
        return RetrofitServiceManager3.api().collect(id)
    }

    override fun unCollect(id: Int): Observable<BaseResponse<Any>> {
        return RetrofitServiceManager3.api().unCollect(id)
    }
}