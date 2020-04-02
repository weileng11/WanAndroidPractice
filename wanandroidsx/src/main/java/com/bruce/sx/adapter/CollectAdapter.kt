package com.bruce.sx.adapter

import android.widget.ImageView
import com.bruce.sx.R
import com.bruce.sx.entity.CollectEntity
import com.bruce.sx.weight.OnLimitClickHelper
import com.bruce.sx.weight.OnLimitClickListener
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * des 收藏适配器
 */
class CollectAdapter(layoutId:Int):BaseQuickAdapter<CollectEntity.DatasBean,BaseViewHolder>(layoutId){

    private var collectClickListener: OnCollectClickListener? = null

    fun setCollectClickListener(collectClickListener:OnCollectClickListener){
        this.collectClickListener = collectClickListener
    }
    override fun convert(helper: BaseViewHolder, item: CollectEntity.DatasBean?) {
        item?.run {
            helper.setText(R.id.tvTag,"")
            helper.setText(R.id.tvAuthor,author)
            helper.setText(R.id.tvDate,niceDate)
            helper.setText(R.id.tvTitle,title)
            helper.setText(R.id.tvChapterName,chapterName)
            helper.getView<ImageView>(R.id.ivCollect)
                .apply {
                    setImageResource(R.mipmap.article_collect)
                    setOnClickListener(OnLimitClickHelper(OnLimitClickListener {
                        collectClickListener?.onCollectClick(helper,helper.adapterPosition)
                    }))
                }
        }
    }

    /**
     * 取消收藏，做单个删除
     */
    fun cancelCollect(position:Int){
        data.removeAt(position)
        notifyItemRemoved(position)
    }

}