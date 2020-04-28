package com.bruce.wanandroidmvvm_sx.data.repository

import com.bruce.wanandroidmvvm_sx.app.network.NetworkApi
import com.bruce.wanandroidmvvm_sx.data.ApiPagerResponse
import com.bruce.wanandroidmvvm_sx.data.ApiResponse
import com.bruce.wanandroidmvvm_sx.data.bean.AriticleResponse
import com.bruce.wanandroidmvvm_sx.data.bean.ClassifyResponse


/**
 * 作者　: hegaojian
 * 时间　: 2020/2/23
 * 描述　:
 */
class PublicRepository {

    //获取公众号标题数据
    suspend fun getTitleData(): ApiResponse<ArrayList<ClassifyResponse>> {
        return NetworkApi().service.getPublicTitle()
    }

    //根据公众号标题获取数据
    suspend fun getPublicData(
        pageNo: Int,
        cid: Int = 0
    ): ApiResponse<ApiPagerResponse<ArrayList<AriticleResponse>>> {
        return NetworkApi().service.getPublicData(pageNo, cid)
    }


}