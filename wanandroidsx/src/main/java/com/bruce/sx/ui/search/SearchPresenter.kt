package com.bruce.sx.ui.search

import com.bruce.sx.base.BasePresenter
import com.bruce.sx.entity.ArticleEntity
import com.bruce.sx.http.HttpCallBack
import com.bruce.sx.http.HttpManager
import com.bruce.sx.http.RetrofitServiceManager3
import io.reactivex.disposables.Disposable

class SearchPresenter(view: SearchContract.View):
    BasePresenter<SearchContract.View>(view) ,
    SearchContract.Presenter<SearchContract.View> {


    override fun search(pageNum: Int, key: String) {
        HttpManager.doHttpRequest(
            RetrofitServiceManager3.api().search(pageNum,key),
            object : HttpCallBack<ArticleEntity> {
                override fun success(rspBean: ArticleEntity?) {
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

    /**
     * 取消收藏
     */
    override fun unCollect(id: Int) {
        HttpManager.doHttpRequest(
            RetrofitServiceManager3.api().unCollect(id),
            object : HttpCallBack<Any> {
                override fun success(rspBean: Any?) {
                    view?.unCollectSuccess()
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

    /**
     * 收藏
     */
    override fun collect(id: Int) {
        HttpManager.doHttpRequest(
            RetrofitServiceManager3.api().collect(id),
            object : HttpCallBack<Any> {
                override fun success(rspBean: Any?) {
                    view?.collectSuccess()
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