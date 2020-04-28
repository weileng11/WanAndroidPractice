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
class ProjectRepository {

    //获取项目标题数据
    suspend fun getBannData(): ApiResponse<ArrayList<ClassifyResponse>> {
        return NetworkApi().service.getProjecTitle()
    }

    //获取项目标题数据
    suspend fun getProjectData(
        pageNo: Int,
        cid: Int = 0,
        isNew: Boolean = false
    ): ApiResponse<ApiPagerResponse<ArrayList<AriticleResponse>>> {
        return if (isNew) {
             NetworkApi().service.getProjecNewData(pageNo)
        } else {
             NetworkApi().service.getProjecDataByType(pageNo, cid)
        }
    }


}