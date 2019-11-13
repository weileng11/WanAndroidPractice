package com.bruce.wanandroid.project.presenter

import com.bruce.wanandroid.apiservice.ApiService
import com.bruce.wanandroid.base.mvp.BasePresenter
import com.bruce.wanandroid.http.BaseObserver
import com.bruce.wanandroid.project.bean.ProjectResponse
import com.bruce.wanandroid.project.contract.ProjectPageContract


class ProjectPagePresenter : BasePresenter<ProjectPageContract.View>(), ProjectPageContract.Presenter {
    override fun getProjectLists(page: Int, cid: Int) {
        addSubscribe(
            create(ApiService::class.java).getProjectLists(page, cid),
            object : BaseObserver<ProjectResponse>() {
                override fun onSuccess(data: ProjectResponse?) {
                    if (this@ProjectPagePresenter.isViewAttached()) {
                        this@ProjectPagePresenter.getView()?.onProjectLists(page, data)
                    }
                }
            })
    }
}