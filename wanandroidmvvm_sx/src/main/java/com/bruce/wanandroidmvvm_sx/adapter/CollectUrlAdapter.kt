package com.bruce.wanandroidmvvm_sx.adapter

import android.text.Html
import com.bruce.wanandroidmvvm_sx.R
import com.bruce.wanandroidmvvm_sx.app.customview.CollectView
import com.bruce.wanandroidmvvm_sx.app.ext.setAdapterAnimion
import com.bruce.wanandroidmvvm_sx.app.util.SettingUtil
import com.bruce.wanandroidmvvm_sx.data.bean.CollectUrlResponse
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder


class CollectUrlAdapter(data: ArrayList<CollectUrlResponse>) :
    BaseQuickAdapter<CollectUrlResponse, BaseViewHolder>(
        R.layout.item_collecturl, data
    ) {

    private var mOnCollectViewClickListener: OnCollectViewClickListener? = null

    init {
        setAdapterAnimion(SettingUtil.getListMode())
    }
    override fun convert(helper: BaseViewHolder, item: CollectUrlResponse) {
        //赋值
        item.run {
            helper.setText(R.id.item_collecturl_name, Html.fromHtml(name))
            helper.setText(R.id.item_collecturl_link, link)
            helper.getView<CollectView>(R.id.item_collecturl_collect).isChecked = true
        }
        helper.getView<CollectView>(R.id.item_collecturl_collect)
            .setOnCollectViewClickListener(object : CollectView.OnCollectViewClickListener {
                override fun onClick(v: CollectView) {
                    mOnCollectViewClickListener?.onClick(item, v, helper.adapterPosition)
                }
            })
    }

    fun setOnCollectViewClickListener(onCollectViewClickListener: OnCollectViewClickListener) {
        mOnCollectViewClickListener = onCollectViewClickListener
    }

    interface OnCollectViewClickListener {
        fun onClick(item: CollectUrlResponse, v: CollectView, position: Int)
    }
}


