package com.bruce.wanandroidmvvm_sx.ui.search

import androidx.lifecycle.MutableLiveData
import com.bruce.wanandroidmvvm_sx.app.CollectViewModel
import com.bruce.wanandroidmvvm_sx.data.ApiPagerResponse
import com.bruce.wanandroidmvvm_sx.data.bean.AriticleResponse
import com.bruce.wanandroidmvvm_sx.data.bean.SearchResponse
import com.bruce.wanandroidmvvm_sx.data.repository.SearchRepository
import me.hgj.jetpackmvvm.state.ResultState
import me.hgj.jetpackmvvm.ext.request

/**
 * 作者　: hegaojian
 * 时间　: 2020/2/29
 * 描述　:
 */
class SearchViewModel : CollectViewModel() {
    var pageNo = 0
    //搜索热词数据
    var hotData: MutableLiveData<ResultState<ArrayList<SearchResponse>>> = MutableLiveData()
    //搜索结果数据
    var seachResultData: MutableLiveData<ResultState<ApiPagerResponse<ArrayList<AriticleResponse>>>> = MutableLiveData()
    //搜索历史词数据
    var historyData: MutableLiveData<ArrayList<String>> = MutableLiveData()

    private  val repository : SearchRepository by lazy { SearchRepository() }

    /**
     * 获取热门数据
     */
    fun getHotData(){
        request({repository.getHotData()},hotData)
    }

    /**
     * 获取历史数据
     */
    fun getHistoryData(){
        //
        historyData.postValue(repository.getHistoryData())
    }

    /**
     * 根据字符串搜索结果
     */
    fun getSearchResultData(searchKey:String,isRefresh:Boolean){
        if(isRefresh){
            pageNo = 0
        }
        request({repository.getSearchResultData(pageNo,searchKey)},seachResultData)
    }
}