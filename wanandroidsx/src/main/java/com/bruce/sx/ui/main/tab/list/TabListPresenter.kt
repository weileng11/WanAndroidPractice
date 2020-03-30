package com.bruce.sx.ui.main.tab.list

import com.bruce.sx.base.BasePresenter
import com.bruce.sx.entity.ArticleEntity
import com.bruce.sx.entity.TabEntity
import com.bruce.sx.http.HttpCallBack
import com.bruce.sx.http.HttpManager
import com.bruce.sx.http.RetrofitServiceManager
import com.zs.wanandroid.constants.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

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
                    RetrofitServiceManager.api().getProjectList(pageNum,id),
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
                    RetrofitServiceManager.api().getAccountList(id,pageNum),
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
        RetrofitServiceManager.api().unCollect(id),
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
            RetrofitServiceManager.api().collect(id),
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