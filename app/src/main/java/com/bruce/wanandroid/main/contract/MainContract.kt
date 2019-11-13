package com.bruce.wanandroid.main.contract

import com.bruce.wanandroid.base.mvp.IView
import com.bruce.wanandroid.db.bean.User

/**
 * @author:
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroid.main.contract
 * @description:
 * @date: 2019/11/13
 * @time:  11:38
 */
interface MainContract {

      interface View : IView {
           fun onUserInfo(user :User)
      }

    interface Presenter {
        fun getUserInfo()
    }
}