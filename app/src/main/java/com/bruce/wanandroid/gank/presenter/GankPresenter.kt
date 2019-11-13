package com.bruce.wanandroid.gank.presenter

import android.util.Log
import com.bruce.wanandroid.apiservice.ApiService
import com.bruce.wanandroid.base.mvp.BasePresenter
import com.bruce.wanandroid.gank.bean.GankToday
import com.bruce.wanandroid.gank.bean.WxPublic
import com.bruce.wanandroid.gank.contract.GankContract
import com.bruce.wanandroid.http.BaseObserver

class GankPresenter : BasePresenter<GankContract.View>(), GankContract.Presenter {

    override fun getGankToday() {
        addSubscribe(create(ApiService::class.java).getGankToday(), object : BaseObserver<HashMap<String, List<GankToday>>>() {
            override fun onSuccess(map: HashMap<String, List<GankToday>>?) {
                Log.e("debug", "map = $map")
                getView()?.onGankToday(map)
            }
        })
    }

    override fun getWxPublic() {
        addSubscribe(create(ApiService::class.java).getWxPublic(), object : BaseObserver<List<WxPublic>>() {
            override fun onSuccess(data: List<WxPublic>?) {
                getView()?.onWxPublic(data)
            }
        })
    }
}