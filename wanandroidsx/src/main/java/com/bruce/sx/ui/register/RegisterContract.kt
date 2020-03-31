package com.bruce.sx.ui.register

import com.bruce.sx.base.IBasePresenter
import com.bruce.sx.base.IBaseView

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.register
 * @description:
 * @date: 2020/3/31
 * @time:  14:21
 */
interface RegisterContract {
    interface View:IBaseView{
        fun registerSuccess()
    }

    interface Presenter<T>: IBasePresenter<View> {
        fun register(username:String,password:String,repassword:String)
    }
}