package com.bruce.sx.ui.share

import com.bruce.sx.base.BasePresenter
import com.bruce.sx.event.ShareEvent
import com.bruce.sx.http.HttpCallBack
import com.bruce.sx.http.HttpManager
import com.bruce.sx.http.RetrofitServiceManager
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus

/**
 * 我的文章
 */
class SharePresenter(view:ShareContract.View): BasePresenter<ShareContract.View>(view)
    , ShareContract.Presenter<ShareContract.View>{

    override fun share(title: String, link: String) {

        HttpManager.doHttpRequest(
            RetrofitServiceManager.api().shareArticle(title,link),
            object : HttpCallBack<Any> {
                override fun success(rspBean: Any?) {
                    EventBus.getDefault().post(ShareEvent())
                    view?.shareSuccess()
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