package com.bruce.wanandroid.project.presenter

import com.bruce.wanandroid.apiservice.ApiService
import com.bruce.wanandroid.base.BaseResponse
import com.bruce.wanandroid.base.mvp.BasePresenter
import com.bruce.wanandroid.home.bean.Article
import com.bruce.wanandroid.home.bean.ArticleResponse
import com.bruce.wanandroid.home.bean.Banner
import com.bruce.wanandroid.http.BaseObserver
import com.bruce.wanandroid.home.contract.HomeContract
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class HomePresenter : BasePresenter<HomeContract.View>(), HomeContract.Presenter {

    private var dataList = ArrayList<Article>()

    //banner
    override fun getBanner() {
        addSubscribe(create(ApiService::class.java).getBanner(), object : BaseObserver<List<Banner>>() {
            override fun onSuccess(data: List<Banner>?) {
                getView()?.onBanner(data)
            }
        })
    }

    //https://www.jianshu.com/p/061f23ecc19a
    override fun getArticles(page: Int) {
        val apiService = create(ApiService::class.java)
        //zip 多个接口数据共同更新 UI
        val zipObservable = Observable.zip(apiService.getTopArticle(), apiService.getArticles(page),
            object :
                BiFunction<BaseResponse<List<Article>>, BaseResponse<ArticleResponse>, BaseResponse<List<Article>>> {
                override fun apply(
                    resp1: BaseResponse<List<Article>>,
                    resp2: BaseResponse<ArticleResponse>
                ): BaseResponse<List<Article>> {
                    if (page == 0) {
                        dataList.clear()
                        val topArticles = resp1.data
                        if (topArticles != null) {
                            dataList.addAll(topArticles)
                        }
                    }
                    val data = resp2.data
                    if (data != null) {
                        val articles = data.datas
                        if (articles != null) {
                            dataList.addAll(articles)
                        }
                    }
                    // 因为 BaseObserver 范型指定了为 BaseResponse， 所以这里重新构造 BaseResponse 对象作为返回值
                    return BaseResponse(dataList, dataList, resp1.errorMsg, resp1.errorCode, false)
                }
            })

        addSubscribe(zipObservable, object : BaseObserver<List<Article>>() {
            override fun onSuccess(data: List<Article>?) {
                getView()?.onArticles(page, data)
            }
        })
    }
}