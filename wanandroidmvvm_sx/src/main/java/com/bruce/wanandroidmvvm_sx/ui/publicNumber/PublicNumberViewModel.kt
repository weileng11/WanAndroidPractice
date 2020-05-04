package com.bruce.wanandroidmvvm_sx.ui.publicNumber

import androidx.lifecycle.MutableLiveData
import com.bruce.wanandroidmvvm_sx.app.CollectViewModel
import com.bruce.wanandroidmvvm_sx.app.network.stateCallback.ListDataUiState
import com.bruce.wanandroidmvvm_sx.data.bean.AriticleResponse
import com.bruce.wanandroidmvvm_sx.data.bean.ClassifyResponse
import com.bruce.wanandroidmvvm_sx.data.repository.PublicRepository
import me.hgj.jetpackmvvm.ext.request
import me.hgj.jetpackmvvm.state.ResultState

/**
 * @author:
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroidmvvm_sx.ui.publicNumber
 * @description:
 * @date: 2020/4/29
 * @time:  16:20
 */
class PublicNumberViewModel : CollectViewModel() {

    var pageNo = 1

    var titleData: MutableLiveData<ResultState<ArrayList<ClassifyResponse>>> = MutableLiveData()

    var publicDataState: MutableLiveData<ListDataUiState<AriticleResponse>> = MutableLiveData()

    private val repository: PublicRepository by lazy { PublicRepository() }

    fun getPublicTitleData() {
        request({ repository.getTitleData() }, titleData)
    }

    fun getPublicData(isRefresh: Boolean, cid: Int) {
        if (isRefresh) {
            pageNo = 1
        }
        request({ repository.getPublicData(pageNo, cid) },{
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
            publicDataState.postValue(listDataUiState)
        },{
            //请求失败
            val listDataUiState =
                ListDataUiState(
                    isSuccess = false,
                    errMessage = it.errorMsg,
                    isRefresh = isRefresh,
                    listData = arrayListOf<AriticleResponse>()
                )
            publicDataState.postValue(listDataUiState)
        })
    }
}