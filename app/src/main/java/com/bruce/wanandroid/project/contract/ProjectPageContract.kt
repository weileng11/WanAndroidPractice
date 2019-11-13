package com.bruce.wanandroid.project.contract

import com.bruce.wanandroid.base.mvp.IView
import com.bruce.wanandroid.project.bean.ProjectResponse


interface ProjectPageContract {
    interface View : IView {
        fun onProjectLists(page: Int, response: ProjectResponse?)
    }

    interface Presenter {
        /**
         * 项目列表
         */
        fun getProjectLists(page: Int, cid: Int)

    }

}