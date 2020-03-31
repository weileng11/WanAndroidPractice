package com.bruce.sx.ui.set

import com.bruce.sx.base.BasePresenter
import com.bruce.sx.http.HttpCallBack
import com.bruce.sx.http.HttpManager
import com.bruce.sx.http.RetrofitServiceManager
import io.reactivex.disposables.Disposable

class SetPresenter(view:SetContract.View) : BasePresenter<SetContract.View>(view)
    ,SetContract.Presenter<SetContract.View> {

    override fun logout() {
        HttpManager.doHttpRequest(
            RetrofitServiceManager.api().logout(),
            object : HttpCallBack<Any> {
                override fun success(rspBean: Any?) {
                    view?.logoutSuccess()
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