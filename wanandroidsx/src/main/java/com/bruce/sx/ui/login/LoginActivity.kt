package com.bruce.sx.ui.login

import android.content.Context
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import com.bruce.sx.R
import com.bruce.sx.base.BaseActivity
import com.bruce.sx.constants.Constants
import com.bruce.sx.ui.register.RegisterActivity
import com.bruce.sx.utils.PrefUtils
import com.bruce.sx.utils.ToastUtils
import com.jeremyliao.liveeventbus.LiveEventBus
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.etPassword
import kotlinx.android.synthetic.main.activity_register.etUsername
import kotlinx.android.synthetic.main.activity_register.indicatorView
import kotlinx.android.synthetic.main.activity_register.ivClear
import kotlinx.android.synthetic.main.activity_register.ivPasswordVisibility

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.login
 * @description:登录
 * @date: 2020/3/26
 * @time:  15:27
 */
class LoginActivity : BaseActivity<LoginContract.Presenter<LoginContract.View>>(),
    LoginContract.View, View.OnClickListener{
    override fun showLoading() {
    }

    override fun closeLoading() {
    }

    /**
     * 是否显示明文
     */
    private var isPasswordShow = false
    override fun init(savedInstanceState: Bundle?) {
        llLogin.setOnClickListener(this)
        tvRegister.setOnClickListener(this)
        tvSkip.setOnClickListener(this)
        ivClear.setOnClickListener(this)
        ivPasswordVisibility.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.llLogin->{
                val username = etUsername.text.toString()
                val password = etPassword.text.toString()
                when {
                    username.isEmpty() -> ToastUtils.show("请输入账号")
                    password.isEmpty() -> ToastUtils.show("请输入密码")
                    else -> {
                        setViewStatus(false)
                        LiveEventBus.get("key").post("value");
                        presenter?.login(username,password)
                    }
                }
            }
            R.id.tvRegister-> intent(RegisterActivity::class.java,false)
            R.id.tvSkip-> finish()
            R.id.ivClear-> {
                etUsername.requestFocus()
                etUsername.setText("")
            }
            R.id.ivPasswordVisibility->{
                etPassword.requestFocus()
                etPassword.transformationMethod = if (isPasswordShow){
                    isPasswordShow = false
                    //显示密码状态
                    ivPasswordVisibility.setImageResource(R.mipmap.password_show)
                    PasswordTransformationMethod.getInstance()

                }else {
                    isPasswordShow = true
                    //显示明文状态,手动将光标移到最后
                    ivPasswordVisibility.setImageResource(R.mipmap.password_hide)
                    HideReturnsTransformationMethod.getInstance()
                }
                etPassword.setSelection(etPassword.text.length)
            }
        }
    }

    /**
     * 登录时给具备点击事件的View上锁，登陆失败时解锁
     * 并且施加动画
     */
    private fun setViewStatus(lockStatus:Boolean){
        llLogin.isEnabled = lockStatus
        tvRegister.isEnabled = lockStatus
        tvSkip.isEnabled = lockStatus
        etUsername.isEnabled = lockStatus
        etPassword.isEnabled = lockStatus
        if (lockStatus) {
            tvLoginTxt.visibility = View.VISIBLE
            indicatorView.visibility = View.GONE
            indicatorView.hide()
        }else {
            tvLoginTxt.visibility = View.GONE
            indicatorView.visibility = View.VISIBLE
            indicatorView.show()
        }
    }


    override fun loginSuccess() {
        PrefUtils.setBoolean(Constants.LOGIN,true)
        finish()
    }

    override fun onError(error: String) {
        setViewStatus(true)
        ToastUtils.show(error)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun createPresenter(): LoginContract.Presenter<LoginContract.View>? {
        return LoginPresenter(this)
    }
    override fun getContext(): Context? {
        return this
    }
}