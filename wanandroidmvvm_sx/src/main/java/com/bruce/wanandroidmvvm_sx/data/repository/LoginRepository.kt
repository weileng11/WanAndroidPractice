package com.bruce.wanandroidmvvm_sx.data.repository

import com.bruce.wanandroidmvvm_sx.app.network.NetworkApi
import com.bruce.wanandroidmvvm_sx.data.ApiResponse
import com.bruce.wanandroidmvvm_sx.data.bean.UserInfo
import me.hgj.jetpackmvvm.network.AppException

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/23
 * 描述　: 登录注册的数据仓库
 */
class LoginRepository {

    //登录
    suspend fun login(username: String, password: String): ApiResponse<UserInfo> {
        return NetworkApi().service.login(username, password)
    }

    //注册并登陆
    suspend fun register(username: String, password: String): ApiResponse<UserInfo> {
        val registerData =  NetworkApi().service.register(username, password,password)
        //判断注册结果 注册成功，调用登录接口
        if(registerData.isSucces()){
            return login(username,password)
        }else{
            //抛出错误异常
            throw AppException(registerData.errorCode,registerData.errorMsg)
        }
    }
}