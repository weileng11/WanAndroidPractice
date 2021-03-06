package com.bruce.sx.ui.main.tab.list

import com.bruce.sx.base.BasePresenter
import com.bruce.sx.constants.Constants
import com.bruce.sx.entity.ArticleEntity
import com.bruce.sx.http.HttpCallBack
import com.bruce.sx.http.HttpManager
import com.bruce.sx.http.RetrofitServiceManager3
import io.reactivex.disposables.Disposable

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.main.tab.list
 * @description:
 * @date: 2020/3/27
 * @time:  17:37
 */
class TabListPresenter(view: TabListContract.View): BasePresenter<TabListContract.View>(view)
    ,TabListContract.Presenter<TabListContract.View>{

    override fun loadData(type:Int,id: Int, pageNum: Int) {
        when(type){
            Constants.PROJECT_TYPE->{
                HttpManager.doHttpRequest(
                    RetrofitServiceManager3.api().getProjectList(pageNum,id),
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
            Constants.ACCOUNT_TYPE->{
                HttpManager.doHttpRequest(
                    RetrofitServiceManager3.api().getAccountList(id,pageNum),
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
        }

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