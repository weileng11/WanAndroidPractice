package com.bruce.wanandroid.project.contract

import com.bruce.wanandroid.base.mvp.IView
import com.bruce.wanandroid.project.bean.ProjectTab


interface ProjectContract {
    interface View : IView {
        fun onProjectTabs(projectTabs: List<ProjectTab>?)
    }

    interface Presenter {
        fun getProjectTabs()
    }

}