package com.bruce.wanandroidmvvm_sx.data.repository

import com.bruce.wanandroidmvvm_sx.app.network.NetworkApi
import com.bruce.wanandroidmvvm_sx.app.util.CacheUtil
import com.bruce.wanandroidmvvm_sx.data.ApiPagerResponse
import com.bruce.wanandroidmvvm_sx.data.ApiResponse
import com.bruce.wanandroidmvvm_sx.data.bean.AriticleResponse
import com.bruce.wanandroidmvvm_sx.data.bean.SearchResponse


/**
 * 作者　: hegaojian
 * 时间　: 2020/2/29
 * 描述　:
 */
class SearchRepository {

    suspend fun getHotData(): ApiResponse<ArrayList<SearchResponse>> {
        return NetworkApi().service.getSearchData()
    }

    suspend fun getSearchResultData(pageNo:Int,searchKey:String): ApiResponse<ApiPagerResponse<ArrayList<AriticleResponse>>> {
        return NetworkApi().service.getSearchDataByKey(pageNo,searchKey)
    }

    fun getHistoryData(): ArrayList<String> {
        return CacheUtil.getSearchHistoryData()
    }
}