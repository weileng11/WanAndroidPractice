package com.bruce.wanandroidmvvm_sx.data.repository

import com.bruce.wanandroidmvvm_sx.app.network.NetworkApi
import com.bruce.wanandroidmvvm_sx.data.ApiPagerResponse
import com.bruce.wanandroidmvvm_sx.data.ApiResponse
import com.bruce.wanandroidmvvm_sx.data.bean.CollectResponse
import com.bruce.wanandroidmvvm_sx.data.bean.CollectUrlResponse


/**
 * 作者　: hegaojian
 * 时间　: 2020/3/3
 * 描述　:
 */
class CollectRepository {

    /**
     * 收藏文章
     */
    suspend fun collect(id: Int): ApiResponse<Any?> {
        return NetworkApi().service.collect(id)
    }

    /**
     * 收藏网址
     */
    suspend fun collectUrl(name: String, link: String): ApiResponse<CollectUrlResponse> {
        return NetworkApi().service.collectUrl(name, link)
    }

    /**
     * 取消收藏文章
     */
    suspend fun uncollect(id: Int): ApiResponse<Any?> {
        return NetworkApi().service.uncollect(id)
    }

    /**
     * 取消收藏网址
     */
    suspend fun uncollectUrl(id: Int): ApiResponse<Any?> {
        return NetworkApi().service.deletetool(id)
    }

    /**
     * 收藏的文章数据
     */
    suspend fun collectAriticleData(pageNo: Int): ApiResponse<ApiPagerResponse<ArrayList<CollectResponse>>>{
        return NetworkApi().service.getCollectData(pageNo)
    }
    /**
     * 收藏的网址数据
     */
    suspend fun collectUrlData(): ApiResponse<ArrayList<CollectUrlResponse>>{
        return NetworkApi().service.getCollectUrlData()
    }
}