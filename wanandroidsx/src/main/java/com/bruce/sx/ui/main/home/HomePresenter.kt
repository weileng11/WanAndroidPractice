package com.bruce.sx.ui.main.home

import com.bruce.sx.base.BasePresenter
import com.bruce.sx.entity.ArticleEntity
import com.bruce.sx.entity.BannerEntity
import com.bruce.sx.http.BaseResponse
import com.bruce.sx.http.HttpCallBack
import com.bruce.sx.http.HttpManager
import com.bruce.sx.http.RetrofitServiceManager
import io.reactivex.disposables.Disposable

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.main.home
 * @description:
 * @date: 2020/3/26
 * @time:  17:22
 */
class HomePresenter(view: HomeContract.View) : BasePresenter<HomeContract.View>(view),
    HomeContract.Presenter<HomeContract.View> {

    /**
     * 加载首页文章列表
     */
    override fun loadData(pageNum: Int) {
        HttpManager.doHttpRequest(RetrofitServiceManager.api().getHomeList(pageNum),
            object : HttpCallBack<ArticleEntity> {
                override fun success(t: ArticleEntity?) {
                    if (pageNum == 0) {
                        t?.datas?.let { loadTopList(it) }
                    } else {
                        t?.datas?.let { view?.showList(it) }
                    }
                }

                override fun error(message: String?) {
                    message?.let { view?.onError(it) }
                }

                override fun disposable(d: Disposable?) {
                    d?.let { addSubscribe(it) }
                }

            })
    }

    /**
     * 包括置顶文章
     */
    private fun loadTopList(list: MutableList<ArticleEntity.DatasBean>) {
        HttpManager.doHttpRequest(RetrofitServiceManager.api().getTopList(),
            object : HttpCallBack<MutableList<ArticleEntity.DatasBean>> {
                //获取置顶文章成功加入到文章列表头部
                override fun success(rspBean: MutableList<ArticleEntity.DatasBean>?) {
                    list.addAll(0, rspBean!!)
                    view?.showList(list)
                }

                //获取置顶文章失败直接返回文章列表
                override fun error(message: String?) {
                    message?.let { view?.onError(it) }
                    view?.showList(list)
                }

                override fun disposable(d: Disposable?) {
                    if (d != null) {
                        addSubscribe(d)
                    }
                }

            })
    }

    /**
     * 由于banner和list位于同一界面
     * 所以banner在presenter内部请求
     */
    override fun loadBanner() {
        HttpManager.doHttpRequest(RetrofitServiceManager.api().getBanner(),
            object : HttpCallBack<MutableList<BannerEntity>> {
                override fun success(rspBean: MutableList<BannerEntity>?) {
                    rspBean?.let { view?.showBanner(it) }
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
        HttpManager.doHttpRequest(RetrofitServiceManager.api().collect(id),
            object : HttpCallBack<BaseResponse<Any>> {
                override fun error(message: String?) {
                    view?.onError(message!!)
                }

                override fun disposable(d: Disposable?) {
                    if (d != null) {
                        addSubscribe(d)
                    }
                }

                override fun success(rspBean: BaseResponse<Any>?) {
                    view?.collectSuccess()
                }
            })
    }

    /**
     * 取消收藏
     */
    override fun unCollect(id: Int) {
        HttpManager.doHttpRequest(RetrofitServiceManager.api().unCollect(id),
            object : HttpCallBack<BaseResponse<Any>> {
                override fun error(message: String?) {
                    view?.onError(message!!)
                }

                override fun disposable(d: Disposable?) {
                    if (d != null) {
                        addSubscribe(d)
                    }
                }

                override fun success(rspBean: BaseResponse<Any>?) {
                    view?.unCollectSuccess()
                }
            })
    }
}