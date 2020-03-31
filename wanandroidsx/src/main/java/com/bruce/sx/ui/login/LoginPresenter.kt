package com.bruce.sx.ui.login

import com.bruce.sx.base.BasePresenter
import com.bruce.sx.entity.TabEntity
import com.bruce.sx.entity.UserEntity
import com.bruce.sx.http.HttpCallBack
import com.bruce.sx.http.HttpManager
import com.bruce.sx.http.RetrofitServiceManager
import com.bruce.sx.utils.PrefUtils
import com.zs.wanandroid.constants.Constants
import com.zs.wanandroid.event.LoginEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.login
 * @description:
 * @date: 2020/3/31
 * @time:  15:02
 */
class LoginPresenter (view: LoginContract.View): BasePresenter<LoginContract.View>(view)
    , LoginContract.Presenter<LoginContract.View> {

    override fun login(username: String, password: String) {

        HttpManager.doHttpRequest(
            RetrofitServiceManager.api().login(username,password),
            object : HttpCallBack<UserEntity> {
                override fun success(rspBean: UserEntity?) {
                    //登陆成功保存用户信息，并发送消息
                    PrefUtils.setObject(Constants.USER_INFO,rspBean)
                    EventBus.getDefault().post(LoginEvent())
                    view?.loginSuccess()
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