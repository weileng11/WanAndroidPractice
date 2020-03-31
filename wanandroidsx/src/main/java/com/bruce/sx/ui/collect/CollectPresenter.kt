package com.bruce.sx.ui.collect

import com.bruce.sx.base.BasePresenter
import com.bruce.sx.entity.CollectEntity
import com.bruce.sx.http.HttpCallBack
import com.bruce.sx.http.HttpManager
import com.bruce.sx.http.RetrofitServiceManager
import io.reactivex.disposables.Disposable

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.collect
 * @description:
 * @date: 2020/3/31
 * @time:  15:28
 */
class CollectPresenter(view:CollectContract.View): BasePresenter<CollectContract.View>(view)
    ,CollectContract.Presenter<CollectContract.View> {


    override fun loadData(page: Int) {

        HttpManager.doHttpRequest(
            RetrofitServiceManager.api().getCollectData(page),
            object : HttpCallBack<CollectEntity> {
                override fun success(rspBean: CollectEntity?) {
                    rspBean?.datas?.let { view?.showList(it) }
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

    override fun cancelCollect(id: Int) {

        HttpManager.doHttpRequest(
            RetrofitServiceManager.api().unCollect(id),
            object : HttpCallBack<Any> {
                override fun success(rspBean: Any?) {
                    view?.cancelCollectSuccess()
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