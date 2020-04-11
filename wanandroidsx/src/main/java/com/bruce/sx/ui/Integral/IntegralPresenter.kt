package com.bruce.sx.ui.Integral

import com.bruce.sx.base.BasePresenter
import com.bruce.sx.entity.IntegralRecordEntity
import com.bruce.sx.http.HttpCallBack
import com.bruce.sx.http.HttpManager
import com.bruce.sx.http.RetrofitServiceManager3
import io.reactivex.disposables.Disposable

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.Integral
 * @description:
 * @date: 2020/3/31
 * @time:  15:14
 */
class IntegralPresenter(view:IntegralContract.View):BasePresenter<IntegralContract.View>(view)
    ,IntegralContract.Presenter<IntegralContract.View>{

    override fun loadData(pageNum:Int) {

        HttpManager.doHttpRequest(
            RetrofitServiceManager3.api().getIntegralRecord(pageNum),
            object : HttpCallBack<IntegralRecordEntity> {
                override fun success(rspBean: IntegralRecordEntity?) {
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