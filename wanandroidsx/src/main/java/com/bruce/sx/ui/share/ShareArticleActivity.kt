package com.bruce.sx.ui.share

import android.content.Context
import android.os.Bundle
import android.view.View
import com.bruce.sx.R
import com.bruce.sx.base.BaseActivity
import com.bruce.sx.utils.DialogUtils
import com.bruce.sx.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_share_articel.*

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.ui.share
 * @description:分享
 * @date: 2020/3/30
 * @time:  16:11
 */
class ShareArticleActivity : BaseActivity<ShareContract.Presenter<ShareContract.View>>(),
    ShareContract.View, View.OnClickListener {

    override fun init(savedInstanceState: Bundle?) {
        ivBack.setOnClickListener(this)
        btShare.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.ivBack->finish()
            R.id.btShare->{
                DialogUtils.loading(this,"正在分享")
                val title = etTitle.text.toString()
                val link = etLink.text.toString()
                presenter?.share(title,link)
            }
        }
    }

    override fun shareSuccess() {
        DialogUtils.dismiss()
        finish()
    }


    override fun onError(error: String) {
        DialogUtils.dismiss()
        ToastUtils.show(error)
    }


    override fun createPresenter(): ShareContract.Presenter<ShareContract.View>? {
        return SharePresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_share_articel
    }

    override fun getContext(): Context? {
        return this
    }
}