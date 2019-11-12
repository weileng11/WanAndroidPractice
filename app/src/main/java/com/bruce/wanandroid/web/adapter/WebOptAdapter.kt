package com.bruce.wanandroid.web.adapter

import com.bruce.wanandroid.R
import com.bruce.wanandroid.web.bean.WebOptBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class WebOptAdapter(layoutResId: Int, dataList: ArrayList<WebOptBean>?) : BaseQuickAdapter<WebOptBean, BaseViewHolder>(layoutResId, dataList) {

    override fun convert(helper: BaseViewHolder?, item: WebOptBean?) {
        val id = item?.resId ?: -1
        if (id != -1) {
            helper?.setImageResource(R.id.iv_web_opt_img, id)
        }

        helper?.setText(R.id.tv_web_opt_title, item?.title)
    }
}