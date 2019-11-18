package com.bruce.wanandroid.user.presenter

import com.bruce.wanandroid.apiservice.ApiService
import com.bruce.wanandroid.base.mvp.BasePresenter
import com.bruce.wanandroid.db.bean.User
import com.bruce.wanandroid.http.BaseObserver
import com.bruce.wanandroid.main.bean.LoggedInEvent
import com.bruce.wanandroid.user.contract.LoginContract
import com.tencent.mm.opensdk.utils.Log
import com.xing.wanandroid.db.DbManager
import org.greenrobot.eventbus.EventBus

class LoginPresenter : BasePresenter<LoginContract.View>(), LoginContract.Presenter {

    override fun login(username: String, password: String) {
        addSubscribe(create(ApiService::class.java).login(username, password), object : BaseObserver<User>(getView()) {
            override fun onSuccess(user: User?) {
                //User{admin=false, collectionIds=null, email='', icon='', id=29136, nickname='13828821554',
                // password='',
                // publicName='13828821554', token='', type=0, username='13828821554'}
                Log.i("INFO","用户数据"+user.toString())
                getView()?.onLoginResult(username, user)
                DbManager.getInstance().getUserDao().deleteAll()
                DbManager.getInstance().getUserDao().insert(user)
                EventBus.getDefault().post(LoggedInEvent(user))
            }
        })
    }
}