package com.bruce.sx.adapter

import com.bruce.sx.R
import com.bruce.sx.entity.SystemListEntity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.donkingliang.labels.LabelsView


/**
 * 体系 适配器
 */
class SystemAdapter(layoutId:Int) :BaseQuickAdapter<SystemListEntity,BaseViewHolder>(layoutId){

    private var systemClickListener: OnSystemClickListener? = null
    fun setOnSystemClickListener(systemClickListener:OnSystemClickListener?){
        this.systemClickListener = systemClickListener
    }
    override fun convert(helper: BaseViewHolder, item: SystemListEntity?) {
        item?.let {
            helper.setText(R.id.tvTitle,item.name)
            helper.getView<LabelsView>(R.id.labelsView).apply {
                setLabels(item.children) { _, _, data ->
                    data.name
                }
                //标签的点击监听
                setOnLabelClickListener { _, _, position ->
                    systemClickListener?.onCollectClick(helper,helper.adapterPosition,position)
                }
            }
        }
    }

}