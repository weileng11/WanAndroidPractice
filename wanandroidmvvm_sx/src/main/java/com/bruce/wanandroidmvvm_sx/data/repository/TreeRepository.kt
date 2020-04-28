package com.bruce.wanandroidmvvm_sx.data.repository

import com.bruce.wanandroidmvvm_sx.app.network.NetworkApi
import com.bruce.wanandroidmvvm_sx.data.ApiPagerResponse
import com.bruce.wanandroidmvvm_sx.data.ApiResponse
import com.bruce.wanandroidmvvm_sx.data.bean.AriticleResponse
import com.bruce.wanandroidmvvm_sx.data.bean.NavigationResponse
import com.bruce.wanandroidmvvm_sx.data.bean.SystemResponse


/**
 * 作者　: hegaojian
 * 时间　: 2020/3/3
 * 描述　:
 */
class TreeRepository {

    /**
     * 获取广场数据
     */
    suspend fun getPlazaData(pageNo:Int): ApiResponse<ApiPagerResponse<ArrayList<AriticleResponse>>> {
        return NetworkApi().service.getSquareData(pageNo)
    }
    /**
     * 获取每日一问数据
     */
    suspend fun getAskData(pageNo:Int):ApiResponse<ApiPagerResponse<ArrayList<AriticleResponse>>>{
        return NetworkApi().service.getAskData(pageNo)
    }
    /**
     * 获取体系数据
     */
    suspend fun getSystemData():ApiResponse<ArrayList<SystemResponse>>{
        return NetworkApi().service.getSystemData()
    }
    /**
     * 获取导航数据
     */
    suspend fun getNavigationData():ApiResponse<ArrayList<NavigationResponse>>{
        return NetworkApi().service.getNavigationData()
    }
    /**
     * 获取体系子数据
     */
    suspend fun getSystemChildData(pageNo:Int,cid:Int):ApiResponse<ApiPagerResponse<ArrayList<AriticleResponse>>>{
        return NetworkApi().service.getSystemChildData(pageNo,cid)
    }


}