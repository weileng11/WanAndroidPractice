package com.bruce.sx.ui.register

import com.bruce.sx.base.BasePresenter
import com.bruce.sx.http.HttpCallBack
import com.bruce.sx.http.HttpManager
import com.bruce.sx.http.RetrofitServiceManager
import io.reactivex.disposables.Disposable

/**
 * @author: brcue
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.register
 * @description:
 * @date: 2020/3/31
 * @time:  14:21
 */
class RegisterPresenter(view:RegisterContract.View):BasePresenter<RegisterContract.View>(view)
    ,RegisterContract.Presenter<RegisterContract.View> {

    override fun register(username: String, password: String, repassword: String) {
        HttpManager.doHttpRequest(
            RetrofitServiceManager.api().register(username,password,repassword),
            object : HttpCallBack<Any> {
                override fun success(rspBean: Any?) {
                    view?.registerSuccess()
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