package com.bruce.wanandroidmvvm_sx.ui.integral

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.bruce.wanandroidmvvm_sx.app.network.stateCallback.ListDataUiState
import com.bruce.wanandroidmvvm_sx.data.bean.IntegralHistoryResponse
import com.bruce.wanandroidmvvm_sx.data.bean.IntegralResponse
import com.bruce.wanandroidmvvm_sx.data.repository.IntegralRepository
import me.hgj.jetpackmvvm.BaseViewModel
import me.hgj.jetpackmvvm.ext.request

/**
 * 作者　: hegaojian
 * 时间　: 2020/3/10
 * 描述　:
 */
class IntegralViewModel :BaseViewModel() {

    private var pageNo = 1
    //积分排行数据
    var integralDataState = MutableLiveData<ListDataUiState<IntegralResponse>>()
    //获取积分历史数据
    var integralHistoryDataState = MutableLiveData<ListDataUiState<IntegralHistoryResponse>>()

    var rank = ObservableField<IntegralResponse>()

    private  val repository : IntegralRepository by lazy { IntegralRepository() }

    fun getIntegralData(isRefresh:Boolean){
        if(isRefresh){
            pageNo = 1
        }
        request({repository.getIntegralData(pageNo)},{
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
            integralDataState.postValue(listDataUiState)
        },{
            //请求失败
            val listDataUiState =
                ListDataUiState(
                    isSuccess = false,
                    errMessage = it.errorMsg,
                    isRefresh = isRefresh,
                    listData = arrayListOf<IntegralResponse>()
                )
            integralDataState.postValue(listDataUiState)
        })
    }

    fun getIntegralHistoryData(isRefresh:Boolean){
        if(isRefresh){
            pageNo = 1
        }
        request({repository.getIntegralHistoryData(pageNo)},{
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
            integralHistoryDataState.postValue(listDataUiState)
        },{
            //请求失败
            val listDataUiState =
                ListDataUiState(
                    isSuccess = false,
                    errMessage = it.errorMsg,
                    isRefresh = isRefresh,
                    listData = arrayListOf<IntegralHistoryResponse>()
                )
            integralHistoryDataState.postValue(listDataUiState)
        })
    }
}