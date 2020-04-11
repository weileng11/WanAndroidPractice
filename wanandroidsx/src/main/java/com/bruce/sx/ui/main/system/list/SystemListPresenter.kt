package com.bruce.sx.ui.main.system.list

import com.bruce.sx.base.BasePresenter
import com.bruce.sx.entity.SystemListEntity
import com.bruce.sx.http.HttpCallBack
import com.bruce.sx.http.HttpManager
import com.bruce.sx.http.RetrofitServiceManager3
import io.reactivex.disposables.Disposable

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.main.system.list
 * @description:
 * @date: 2020/3/30
 * @time:  15:07
 */
class SystemListPresenter(view:SystemListContract.View):BasePresenter<SystemListContract.View>(view),
    SystemListContract.Presenter<SystemListContract.View>{

    override fun loadData() {
        HttpManager.doHttpRequest(
            RetrofitServiceManager3.api().getSystemList(),
            object : HttpCallBack<MutableList<SystemListEntity>> {
                override fun success(rspBean: MutableList<SystemListEntity>?) {
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