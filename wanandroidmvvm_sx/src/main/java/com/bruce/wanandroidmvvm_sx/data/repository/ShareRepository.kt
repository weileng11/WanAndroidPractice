package com.bruce.wanandroidmvvm_sx.data.repository

import com.bruce.wanandroidmvvm_sx.app.network.NetworkApi
import com.bruce.wanandroidmvvm_sx.data.ApiResponse
import com.bruce.wanandroidmvvm_sx.data.bean.ShareResponse


/**
 * 作者　: hegaojian
 * 时间　: 2020/3/5
 * 描述　:
 */
class ShareRepository  {

    suspend fun getLookinfoById(id:Int, pageNo:Int): ApiResponse<ShareResponse> {
        return NetworkApi().service.getShareByidData(pageNo,id)
    }
}