package com.bruce.wanandroid.meizi.adapter

import android.widget.ImageView
import com.bruce.wanandroid.R
import com.bruce.wanandroid.meizi.bean.Meizi
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class MeiziAdapter(layoutResId: Int) : BaseQuickAdapter<Meizi, BaseViewHolder>(layoutResId) {

    override fun convert(helper: BaseViewHolder?, item: Meizi?) {
        val imageView: ImageView? = helper?.getView(R.id.iv_meizi)
        Glide.with(mContext).load(item?.url).into(imageView!!)
    }
}