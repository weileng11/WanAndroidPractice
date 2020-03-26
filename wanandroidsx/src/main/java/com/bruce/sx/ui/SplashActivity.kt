package com.bruce.sx.ui

import android.Manifest
import android.os.Bundle
import com.bruce.sx.ui.main.MainActivity
import com.bruce.sx.R
import com.bruce.sx.base.BaseActivity
import com.bruce.sx.base.IBasePresenter
import com.bruce.sx.proxy.IConfirmClickCallBack
import com.bruce.sx.utils.DialogUtils
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import java.util.concurrent.TimeUnit

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui
 * @description:启动页
 * @date: 2020/3/26
 * @time:  11:28
 */
class SplashActivity : BaseActivity<IBasePresenter<*>>() , EasyPermissions.PermissionCallbacks {

    private var disposable: Disposable? = null
    private val tips = "玩安卓现在要向您申请存储权限，用于存储历史记录以及保存小姐姐图片，您也可以在设置中手动开启或者取消。"
    private val perms= arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

    companion object {
        private const val WRITE_EXTERNAL_STORAGE = 100
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun init(savedInstanceState: Bundle?) {
//        saveIntegral()
        requestPermission()
    }

    override fun createPresenter(): IBasePresenter<*>? {
        return null
    }

    /**
     * 开始倒计时跳转
     */
    private fun startIntent(){
        disposable = Observable.timer(2000, TimeUnit.MILLISECONDS)
            .subscribe {
                intent(MainActivity::class.java,false)
                finish()
            }
    }

    /**
     * 申请权限
     */
    private fun requestPermission(){
        //已申请
        if (EasyPermissions.hasPermissions(this, *perms)) {
            startIntent()
        }else{
            //为申请，显示申请提示语
            DialogUtils.tips(this,tips,object : IConfirmClickCallBack {
                override fun onClick() {
                    RequestLocationAndCallPermission()
                }
            })
        }
    }

    @AfterPermissionGranted(WRITE_EXTERNAL_STORAGE)
    private fun RequestLocationAndCallPermission() {
        val perms = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (EasyPermissions.hasPermissions(this, *perms)) {
            startIntent()
        } else {
            EasyPermissions.requestPermissions(this, "请求写入权限", WRITE_EXTERNAL_STORAGE, *perms)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }


//    /**
//     * 保存积分信息
//     */
//    private fun saveIntegral(){
//        RetrofitHelper.getApiService()
//            .getIntegral()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : HttpDefaultObserver<IntegralEntity>() {
//                override fun onSuccess(t: IntegralEntity) {
//                    PrefUtils.setObject(Constants.INTEGRAL_INFO,t)
//                }
//                override fun onError(errorMsg: String) {
//                }
//
//                override fun disposable(d: Disposable) {
//                }
//            })
//    }

    /**
     * 权限申请失败
     */
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
    }

    /**
     * 权限申请成功
     */
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        startIntent()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

}