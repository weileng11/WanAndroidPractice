package com.bruce.sx.ui.main.tab

import com.bruce.sx.base.BasePresenter
import com.bruce.sx.constants.Constants
import com.bruce.sx.entity.TabEntity
import com.bruce.sx.http.HttpCallBack
import com.bruce.sx.http.HttpManager
import com.bruce.sx.http.RetrofitServiceManager
import io.reactivex.disposables.Disposable

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.main.tab
 * @description:
 * @date: 2020/3/27
 * @time:  17:21
 */
class TabPresenter(view: TabContract.View) : BasePresenter<TabContract.View>(view)
    , TabContract.Presenter<TabContract.View> {

    override fun loadData(type: Int) {
        when(type){
            //项目
            Constants.PROJECT_TYPE->{
                HttpManager.doHttpRequest(
                    RetrofitServiceManager.api().getProjectTabList(),
                    object : HttpCallBack<MutableList<TabEntity>> {
                        override fun success(rspBean: MutableList<TabEntity>?) {
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
            //公众号
            Constants.ACCOUNT_TYPE->{
                HttpManager.doHttpRequest(
                    RetrofitServiceManager.api().getAccountTabList(),
                    object : HttpCallBack<MutableList<TabEntity>> {
                        override fun success(rspBean: MutableList<TabEntity>?) {
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
    }
}