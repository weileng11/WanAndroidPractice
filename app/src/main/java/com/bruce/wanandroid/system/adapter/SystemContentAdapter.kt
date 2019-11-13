package com.bruce.wanandroid.system.adapter

import com.bruce.wanandroid.R
import com.bruce.wanandroid.system.bean.SystemCategory
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class SystemContentAdapter(layoutResId: Int) : BaseQuickAdapter<SystemCategory, BaseViewHolder>(layoutResId) {

    override fun convert(helper: BaseViewHolder?, item: SystemCategory?) {
        helper?.setText(R.id.tv_system_category, item?.name)
    }
}


