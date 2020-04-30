package com.bruce.wanandroidmvvm_sx.ui.project

import androidx.lifecycle.MutableLiveData
import com.bruce.wanandroidmvvm_sx.app.CollectViewModel
import com.bruce.wanandroidmvvm_sx.app.network.stateCallback.ListDataUiState
import com.bruce.wanandroidmvvm_sx.data.bean.AriticleResponse
import com.bruce.wanandroidmvvm_sx.data.bean.ClassifyResponse
import com.bruce.wanandroidmvvm_sx.data.repository.ProjectRepository
import me.hgj.jetpackmvvm.ext.request
import me.hgj.jetpackmvvm.state.ResultState

/**
 * @author:
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroidmvvm_sx.ui.project
 * @description:
 * @date: 2020/4/29
 * @time:  16:17
 */
class ProjectViewModel : CollectViewModel() {
    //页码
    var pageNo = 1

    var titleData: MutableLiveData<ResultState<ArrayList<ClassifyResponse>>> = MutableLiveData()

    var projectDataState: MutableLiveData<ListDataUiState<AriticleResponse>> = MutableLiveData()

    private val repository: ProjectRepository by lazy { ProjectRepository() }

    //标题的数据
    fun getProjectTitleData() {
        request({ repository.getBannData() }, titleData)
    }

    //项目数据
    fun getProjectData(isRefresh: Boolean, cid: Int, isNew: Boolean = false) {
        if (isRefresh) {
            pageNo = if (isNew) 0 else 1
        }
        request({repository.getProjectData(pageNo, cid, isNew)},{
            //请求成功
            pageNo++
            val listDataUiState =
                ListDataUiState(
                    isSuccess = true,
                    isRefresh = isRefresh,
                    isEmpty = it.isEmpty(),
                    hasMore = it.hasMore(),
                    isFirstEmpty = isRefresh && it.isEmpty(),
                    listData = it.datas
                )
            projectDataState.postValue(listDataUiState)
        },{
            //请求失败
            val listDataUiState =
                ListDataUiState(
                    isSuccess = false,
                    errMessage = it.errorMsg,
                    isRefresh = isRefresh,
                    listData = arrayListOf<AriticleResponse>()
                )
            projectDataState.postValue(listDataUiState)
        })
    }
}