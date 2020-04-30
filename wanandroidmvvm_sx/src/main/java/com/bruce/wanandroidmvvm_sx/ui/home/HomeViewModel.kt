package com.bruce.wanandroidmvvm_sx.ui.home

import androidx.lifecycle.MutableLiveData
import com.bruce.wanandroidmvvm_sx.app.CollectViewModel
import com.bruce.wanandroidmvvm_sx.app.network.stateCallback.ListDataUiState
import com.bruce.wanandroidmvvm_sx.data.bean.AriticleResponse
import com.bruce.wanandroidmvvm_sx.data.bean.BannerResponse
import com.bruce.wanandroidmvvm_sx.data.repository.HomeRepository
import me.hgj.jetpackmvvm.ext.request
import me.hgj.jetpackmvvm.state.ResultState

/**
 * @author:
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroidmvvm_sx.ui.home
 *  * 描述　: 有两种回调方式：
 * 1.首页文章列表 将返回的数据放在Viewmodel中过滤包装给activity/fragment去使用
 * 2.首页轮播图 将返回的数据直接给activity/fragment去处理使用
 * 可以根据个人理解与喜好使用
 * @date: 2020/4/29
 * @time:  16:10
 */
class HomeViewModel : CollectViewModel() {
    //页码 首页数据页码从0开始
    var pageNo: Int = 0
    //首页文章列表数据
    var homeDataState: MutableLiveData<ListDataUiState<AriticleResponse>> = MutableLiveData()
    //首页轮播图数据
    var bannerData: MutableLiveData<ResultState<ArrayList<BannerResponse>>> = MutableLiveData()
    //主页的请求数据仓库
    private val homeRepository : HomeRepository by lazy { HomeRepository() }

    /**
     * 获取首页文章列表数据
     * @param isRefresh 是否是刷新，即第一页
     */
    fun getHomeData(isRefresh: Boolean) {
        if (isRefresh) {
            pageNo = 0
        }
        request({ homeRepository.getHomeData(pageNo) },{
            //请求成功
            pageNo++
            val listDataUiState =
                ListDataUiState(
                    isSuccess = true,
                isRefresh = isRefresh,
                isEmpty = it.isEmpty(),
                hasMore = it.hasMore(),
                isFirstEmpty = isRefresh && it.isEmpty(),
                listData = it.datas)
            homeDataState.postValue(listDataUiState)
        },{
            //请求失败
            val listDataUiState =
                ListDataUiState(
                    isSuccess = false,
                    errMessage = it.errorMsg,
                    isRefresh = isRefresh,
                    listData = arrayListOf<AriticleResponse>()
                )
            homeDataState.postValue(listDataUiState)
        })
    }

    /**
     * 获取轮播图数据
     */
    fun getBannerData() {
        request({ homeRepository.getBannData() }, bannerData)
    }
}