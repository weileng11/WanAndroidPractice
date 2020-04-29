package com.bruce.wanandroidmvvm_sx.ui.me

import androidx.lifecycle.MutableLiveData
import com.bruce.wanandroidmvvm_sx.data.bean.IntegralResponse
import com.bruce.wanandroidmvvm_sx.data.repository.MeRepository
import me.hgj.jetpackmvvm.BaseViewModel
import me.hgj.jetpackmvvm.databind.IntObservableField
import me.hgj.jetpackmvvm.databind.StringObservableField
import me.hgj.jetpackmvvm.ext.request
import me.hgj.jetpackmvvm.state.ResultState

/**
 * @author:
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroidmvvm_sx.ui.me
 * @description:
 * @date: 2020/4/29
 * @time:  16:15
 */
class MeViewModel : BaseViewModel() {
    private val repository: MeRepository by lazy { MeRepository() }

    var name = StringObservableField("请先登录~")

    var integral = IntObservableField(0)

    var info = StringObservableField("id：--　排名：-")

    var imageUrl = StringObservableField("https://upload.jianshu.io/users/upload_avatars/9305757/93322613-ff1a-445c-80f9-57f088f7c1b1.jpg?imageMogr2/auto-orient/strip|imageView2/1/w/300/h/300/format/webp")

    var meData = MutableLiveData<ResultState<IntegralResponse>>()

    fun getIntegral() {
        request({ repository.getIntegral() }, meData)
    }
}