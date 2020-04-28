package com.bruce.wanandroidmvvm_sx.data.repository

import com.bruce.wanandroidmvvm_sx.app.network.NetworkApi
import com.bruce.wanandroidmvvm_sx.data.ApiResponse
import com.bruce.wanandroidmvvm_sx.data.bean.IntegralResponse


/**
 * 作者　: hegaojian
 * 时间　: 2019/12/23
 */
class MeRepository {
    suspend fun getIntegral(): ApiResponse<IntegralResponse> {
        return NetworkApi().service.getIntegral()
    }
}