package com.bruce.sx.ui.my

import com.bruce.sx.base.BasePresenter
import com.bruce.sx.entity.MyArticleEntity
import com.bruce.sx.http.HttpCallBack
import com.bruce.sx.http.HttpManager
import com.bruce.sx.http.RetrofitServiceManager3
import io.reactivex.disposables.Disposable

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.my
 * @description:
 * @date: 2020/3/31
 * @time:  14:54
 */
class  MyArticlePresenter(view:MyArticleContract.View): BasePresenter<MyArticleContract.View>(view)
,MyArticleContract.Presenter<MyArticleContract.View> {

    override fun loadData(pageNum: Int) {
        HttpManager.doHttpRequest(
            RetrofitServiceManager3.api().getMyArticle(pageNum),
            object : HttpCallBack<MyArticleEntity> {
                override fun success(rspBean: MyArticleEntity?) {
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

    override fun delete(id: Int) {
        HttpManager.doHttpRequest(
            RetrofitServiceManager3.api().deleteMyArticle(id),
            object : HttpCallBack<Any> {
                override fun success(rspBean: Any?) {
                    view?.deleteSuccess()
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