package com.bruce.wanandroid.gank.adapter

import com.bruce.wanandroid.R
import com.bruce.wanandroid.gank.bean.WxPublic
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class WxPublicAdapter : BaseQuickAdapter<WxPublic, BaseViewHolder> {

    constructor(layoutResId: Int) : super(layoutResId)

    constructor(layoutResId: Int, list: List<WxPublic>) : super(layoutResId, list)

    override fun convert(helper: BaseViewHolder?, item: WxPublic?) {
//        val name: String = item?.name ?: ""
        val name:String =item?.name?:return
        helper?.setText(R.id.tv_wx_author, name)
            ?.setText(R.id.tv_wx_author_icon, name.substring(0, 1))
    }
}