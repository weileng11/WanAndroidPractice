package com.bruce.wanandroidmvvm_sx.data.repository

import com.bruce.wanandroidmvvm_sx.app.network.NetworkApi
import com.bruce.wanandroidmvvm_sx.data.ApiPagerResponse
import com.bruce.wanandroidmvvm_sx.data.ApiResponse
import com.bruce.wanandroidmvvm_sx.data.bean.IntegralHistoryResponse
import com.bruce.wanandroidmvvm_sx.data.bean.IntegralResponse


/**
 * 作者　: hegaojian
 * 时间　: 2020/3/10
 * 描述　:积分数据仓库
 */
class IntegralRepository {

    suspend  fun getIntegralData(pageNo:Int): ApiResponse<ApiPagerResponse<ArrayList<IntegralResponse>>> {
       return   NetworkApi().service.getIntegralRank(pageNo)
    }

    suspend  fun getIntegralHistoryData(pageNo:Int):ApiResponse<ApiPagerResponse<ArrayList<IntegralHistoryResponse>>>{
        return   NetworkApi().service.getIntegralHistory(pageNo)
    }
}