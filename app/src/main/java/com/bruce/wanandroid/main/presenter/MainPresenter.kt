package com.bruce.wanandroid.main.presenter

import android.util.Log
import com.bruce.wanandroid.base.mvp.BasePresenter
import com.bruce.wanandroid.main.contract.MainContract
import com.xing.wanandroid.db.DbManager

/**
 * @author: Bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroid.main.presenter
 * @description:
 * @date: 2019/11/13
 * @time:  11:47
 */
class MainPresenter :BasePresenter<MainContract.View>(),MainContract.Presenter{

    override fun getUserInfo() {
       val users= DbManager.getInstance().getUserDao().loadAll()
        Log.e("MainPresenter", "users = $users")
        if (users.size > 0) {
            getView()?.onUserInfo(users[0])
        }
    }

}