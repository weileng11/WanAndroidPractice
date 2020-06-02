package com.bruce.wanandroidmvvm_sx.ui.login

import android.app.Activity
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.bruce.wanandroidmvvm_sx.R
import com.bruce.wanandroidmvvm_sx.app.base.BaseFragment
import com.bruce.wanandroidmvvm_sx.app.ext.hideSoftKeyboard
import com.bruce.wanandroidmvvm_sx.app.ext.initClose
import com.bruce.wanandroidmvvm_sx.app.ext.showMessage
import com.bruce.wanandroidmvvm_sx.app.util.CacheUtil
import com.bruce.wanandroidmvvm_sx.app.util.SettingUtil
import com.bruce.wanandroidmvvm_sx.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.include_toolbar.*
import me.hgj.jetpackmvvm.ext.parseState
import me.hgj.jetpackmvvm.ext.util.setOnclickNoRepeat

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroidmvvm_sx.ui.login
 * @description:登录
 * @date: 2020/5/4
 * @time:  17:06
 */
class LoginFragment : BaseFragment<LoginRegisterViewModel, FragmentLoginBinding>() {

    override fun layoutId() = R.layout.fragment_login

    override fun initView() {
        mDatabind.viewmodel = mViewModel

        toolbar.initClose("登录") {
            Navigation.findNavController(it).navigateUp()
        }

        //设置颜色跟主题颜色一致
        appViewModel.appColor.value?.let {
            SettingUtil.setShapColor(loginSub, it)
            loginGoregister.setTextColor(it)
            toolbar.setBackgroundColor(it)
        }
    }

    override fun createObserver() {
        //监听请求结果
        mViewModel.loginResult2.observe(viewLifecycleOwner, Observer { resultState ->
            parseState(resultState, {
                //登录成功 通知账户数据发生改变鸟
                CacheUtil.setUser(it.data)
                appViewModel.userinfo.postValue(it.data)
                Navigation.findNavController(toolbar).navigateUp()
//                startActivity(Intent(activity, MainActivity::class.java))
            }, {
                //登录失败
                showMessage(it.errorMsg)
            },{})
        })
    }

    override fun lazyLoadData() { }
    /**
     * 设置点击事件 ,根据黄油刀的风格来仿写
     */
    override fun onViewClicked() {
        setOnclickNoRepeat(loginClear, loginSub, loginGoregister) {
            when (it.id) {
                R.id.loginClear -> {
                    mViewModel.username.set("")
                }
                R.id.loginSub -> {
                    when {
                        mViewModel.username.get().isEmpty() -> showMessage("请填写账号")
                        mViewModel.password.get().isEmpty() -> showMessage("请填写密码")
                        else -> mViewModel.loginReq()
                    }
                }
                R.id.loginGoregister -> {
                    hideSoftKeyboard(activity as Activity?)
                    Navigation.findNavController(it)
                        .navigate(R.id.action_loginFragment_to_registerFrgment)
                }
            }
        }
        loginKey.setOnCheckedChangeListener { buttonView, isChecked ->
            mViewModel.isShowPwd.set(
                isChecked
            )
        }
    }
}