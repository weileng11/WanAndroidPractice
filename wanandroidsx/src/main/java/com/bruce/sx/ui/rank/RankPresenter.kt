package com.bruce.sx.ui.rank

import com.bruce.sx.base.BasePresenter
import com.bruce.sx.entity.RankEntity
import com.bruce.sx.http.HttpCallBack
import com.bruce.sx.http.HttpManager
import com.bruce.sx.http.RetrofitServiceManager
import io.reactivex.disposables.Disposable

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.rank
 * @description:
 * @date: 2020/3/31
 * @time:  14:37
 */
class RankPresenter(view:RankContract.View): BasePresenter<RankContract.View>(view)
    ,RankContract.Presenter<RankContract.View> {

    override fun loadData(pageNum: Int) {
        HttpManager.doHttpRequest(
            RetrofitServiceManager.api().getRank(pageNum),
            object : HttpCallBack<RankEntity> {
                override fun success(rspBean: RankEntity?) {
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