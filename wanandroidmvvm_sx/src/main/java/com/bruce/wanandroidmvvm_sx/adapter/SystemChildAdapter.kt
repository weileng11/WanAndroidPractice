package com.bruce.wanandroidmvvm_sx.adapter

import android.text.Html
import com.bruce.wanandroidmvvm_sx.R
import com.bruce.wanandroidmvvm_sx.app.ext.setAdapterAnimion
import com.bruce.wanandroidmvvm_sx.app.util.ColorUtil
import com.bruce.wanandroidmvvm_sx.app.util.SettingUtil
import com.bruce.wanandroidmvvm_sx.data.bean.ClassifyResponse
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class SystemChildAdapter(data: ArrayList<ClassifyResponse>) :
    BaseQuickAdapter<ClassifyResponse, BaseViewHolder>(R.layout.flow_layout, data) {

    init {
        setAdapterAnimion(SettingUtil.getListMode())
    }

    override fun convert(helper: BaseViewHolder, item: ClassifyResponse) {
        helper.setText(R.id.flow_tag, Html.fromHtml(item.name))
        helper.setTextColor(R.id.flow_tag, ColorUtil.randomColor())
    }

}