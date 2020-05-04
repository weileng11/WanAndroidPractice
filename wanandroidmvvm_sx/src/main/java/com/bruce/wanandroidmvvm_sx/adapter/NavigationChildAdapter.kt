package com.bruce.wanandroidmvvm_sx.adapter

import android.text.Html
import com.bruce.wanandroidmvvm_sx.R
import com.bruce.wanandroidmvvm_sx.app.ext.setAdapterAnimion
import com.bruce.wanandroidmvvm_sx.app.util.ColorUtil
import com.bruce.wanandroidmvvm_sx.app.util.SettingUtil
import com.bruce.wanandroidmvvm_sx.data.bean.AriticleResponse
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class NavigationChildAdapter(data: ArrayList<AriticleResponse>) :
    BaseQuickAdapter<AriticleResponse, BaseViewHolder>(R.layout.flow_layout, data) {

    init {
        setAdapterAnimion(SettingUtil.getListMode())
    }

    override fun convert(helper: BaseViewHolder, item: AriticleResponse) {
        helper.setText(R.id.flow_tag, Html.fromHtml(item.title))
        helper.setTextColor(R.id.flow_tag, ColorUtil.randomColor())
    }

}