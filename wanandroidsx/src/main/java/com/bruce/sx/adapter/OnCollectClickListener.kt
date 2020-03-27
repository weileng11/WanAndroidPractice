package com.zs.wanandroid.adapter

import com.chad.library.adapter.base.BaseViewHolder

/**
 * 收藏点击事件
 */
interface OnCollectClickListener {
    fun onCollectClick(helper: BaseViewHolder, position: Int)
}