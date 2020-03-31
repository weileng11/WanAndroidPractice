package com.bruce.sx.ui.main.mine

import com.bruce.sx.base.BasePresenter
import com.bruce.sx.entity.IntegralEntity
import com.bruce.sx.entity.SystemListEntity
import com.bruce.sx.http.HttpCallBack
import com.bruce.sx.http.HttpManager
import com.bruce.sx.http.RetrofitServiceManager
import com.bruce.sx.utils.PrefUtils
import com.zs.wanandroid.constants.Constants
import io.reactivex.disposables.Disposable

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.main.mine
 * @description:
 * @date: 2020/3/30
 * @time:  15:58
 */
class MinePresenter(view:MineContract.View):BasePresenter<MineContract.View>(view)
,MineContract.Presenter<MineContract.View>{

    override fun loadIntegral() {

        HttpManager.doHttpRequest(
            RetrofitServiceManager.api().getIntegral(),
            object : HttpCallBack<IntegralEntity> {
                override fun success(rspBean: IntegralEntity?) {
                    PrefUtils.setObject(Constants.INTEGRAL_INFO,rspBean)
                    rspBean?.let { view?.showIntegral(it) }
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