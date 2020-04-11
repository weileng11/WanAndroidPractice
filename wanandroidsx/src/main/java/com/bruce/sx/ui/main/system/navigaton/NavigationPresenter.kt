package com.bruce.sx.ui.main.system.navigaton

import com.bruce.sx.base.BasePresenter
import com.bruce.sx.entity.NavigationEntity
import com.bruce.sx.http.HttpCallBack
import com.bruce.sx.http.HttpManager
import com.bruce.sx.http.RetrofitServiceManager3
import io.reactivex.disposables.Disposable

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.main.system.navigaton
 * @description:导航
 * @date: 2020/3/30
 * @time:  15:01
 */
class NavigationPresenter(view:NavigationContract.View):  BasePresenter<NavigationContract.View>(view)
,NavigationContract.Presenter<NavigationContract.View>{

    override fun loadData() {
        HttpManager.doHttpRequest(
            RetrofitServiceManager3.api().getNavigation(),
            object : HttpCallBack<MutableList<NavigationEntity>> {
                override fun success(rspBean: MutableList<NavigationEntity>?) {
                    rspBean?.let { view?.showList(it) }
                }

                override fun error(message: String?) {
                    view?.onError(message!!)
                }

                override fun disposable(d: Disposable?) {
                    if (d != null) {
                        addSubscribe(d)
                    }
                }
            })
    }
}