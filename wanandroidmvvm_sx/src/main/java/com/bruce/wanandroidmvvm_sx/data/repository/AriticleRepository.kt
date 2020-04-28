package com.bruce.wanandroidmvvm_sx.data.repository

import com.bruce.wanandroidmvvm_sx.app.network.NetworkApi
import com.bruce.wanandroidmvvm_sx.data.ApiResponse
import com.bruce.wanandroidmvvm_sx.data.bean.ShareResponse


/**
 * 作者　: hegaojian
 * 时间　: 2020/3/11
 * 描述　:
 */
class AriticleRepository {
    suspend fun addAriticle(title:String,content:String): ApiResponse<Any?> {
        return NetworkApi().service.addAriticle(title,content)
    }
    suspend fun getShareAriticle(pageNo:Int): ApiResponse<ShareResponse> {
        return NetworkApi().service.getShareData(pageNo)
    }
    suspend fun delShareAriticle(id:Int): ApiResponse<Any?> {
        return NetworkApi().service.deleteShareData(id)
    }
}