package com.bruce.wanandroid.project.presenter

import com.bruce.wanandroid.apiservice.ApiService
import com.bruce.wanandroid.base.mvp.BasePresenter
import com.bruce.wanandroid.http.BaseObserver
import com.bruce.wanandroid.project.bean.ProjectTab
import com.bruce.wanandroid.project.contract.ProjectContract

class ProjectPresenter : BasePresenter<ProjectContract.View>(), ProjectContract.Presenter {

    override fun getProjectTabs() {
        addSubscribe(create(ApiService::class.java).getProjectTabs(), object : BaseObserver<List<ProjectTab>>() {
            override fun onSuccess(data: List<ProjectTab>?) {
                if (this@ProjectPresenter.isViewAttached()) {
                    this@ProjectPresenter.getView()?.onProjectTabs(data)
                }
            }
        })
    }
}