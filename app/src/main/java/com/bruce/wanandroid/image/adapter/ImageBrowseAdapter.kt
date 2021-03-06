package com.bruce.wanandroid.image.adapter

import com.bruce.wanandroid.R
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.github.chrisbanes.photoview.PhotoView

class ImageBrowseAdapter(layoutResId: Int) : BaseQuickAdapter<String, BaseViewHolder>(layoutResId) {

    override fun convert(helper: BaseViewHolder?, item: String?) {
        val photoView: PhotoView? = helper?.getView(R.id.pv_image)
        Glide.with(mContext).load(item ?: "").into(photoView!!)
    }
}