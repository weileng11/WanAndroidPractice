package com.bruce.wanandroid.gank.adapter

import android.widget.ImageView
import com.bruce.wanandroid.R
import com.bruce.wanandroid.gank.bean.GankToday
import com.bruce.wanandroid.gank.bean.GankTodayEntity
import com.bruce.wanandroid.utils.createColorDrawable
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

//https://blog.csdn.net/u013064109/article/details/78786646
class GankTodayAdapter(layoutResId: Int, sectionHeadResId: Int, list: List<GankTodayEntity<GankToday>>) : BaseSectionQuickAdapter<GankTodayEntity<GankToday>, BaseViewHolder>(layoutResId, sectionHeadResId, list) {

    //头部
    override fun convertHead(helper: BaseViewHolder?, item: GankTodayEntity<GankToday>?) {
        helper?.setText(R.id.tv_gank_section_head, item?.header)
    }

    override fun convert(helper: BaseViewHolder?, item: GankTodayEntity<GankToday>?) {
        val publishedAt: String? = item?.t?.publishedAt
        val publishedAtString: String
        when {
            publishedAt == null -> publishedAtString = ""
            publishedAt.length < 10 -> publishedAtString = publishedAt
            else -> publishedAtString = publishedAt.substring(0, 10)
        }


        val item1=item?.t ?:return
//        item1?.run {
//            helper?.setText(R.id.tv_gank_desc, desc)
//            ?.setText(R.id.tv_gank_who, who)
//            ?.setText(R.id.tv_gank_create_time, publishedAtString)
//            ?.setGone(R.id.ll_gank_images_parent, images != null && images?.size != 0)
//        }

//        with(item1){
//            helper?.setText(R.id.tv_gank_desc, desc)
//                ?.setText(R.id.tv_gank_who, who)
//                ?.setText(R.id.tv_gank_create_time, publishedAtString)
//                ?.setGone(R.id.ll_gank_images_parent, images != null && images?.size != 0)
//        }

        item1?.apply {
            helper?.setText(R.id.tv_gank_desc, desc)
                ?.setText(R.id.tv_gank_who, who)
                ?.setText(R.id.tv_gank_create_time, publishedAtString)
                ?.setGone(R.id.ll_gank_images_parent, images != null && images?.size != 0)
        }



//        helper?.setText(R.id.tv_gank_desc, item?.t?.desc)
//            ?.setText(R.id.tv_gank_who, item?.t?.who)
//            ?.setText(R.id.tv_gank_create_time, publishedAtString)
//            ?.setGone(R.id.ll_gank_images_parent, item?.t?.images != null && item.t?.images?.size != 0)

        val leftDrawable = createColorDrawable(mContext)
        val centerDrawable = createColorDrawable(mContext)
        val rightDrawable = createColorDrawable(mContext)
        if (item?.t?.images?.size ?: 0 > 2) {
            val leftImgView: ImageView? = helper?.getView(R.id.iv_gank_left)
            val centerImgView: ImageView? = helper?.getView(R.id.iv_gank_center)
            val rightImgView: ImageView? = helper?.getView(R.id.iv_gank_right)
            if (leftImgView != null) {
                Glide.with(mContext)
                    .load(item?.t?.images?.get(0))
                    .placeholder(leftDrawable)
                    .error(leftDrawable)
                    .into(leftImgView)
            }
            if (centerImgView != null) {
                Glide.with(mContext)
                    .load(item?.t?.images?.get(0))
                    .placeholder(leftDrawable)
                    .error(leftDrawable)
                    .into(centerImgView)
            }
            if (rightImgView != null) {
                Glide.with(mContext)
                    .load(item?.t?.images?.get(0))
                    .placeholder(leftDrawable)
                    .error(leftDrawable)
                    .into(rightImgView)
            }
        } else if (item?.t?.images?.size ?: 0 == 1) {
            val leftImgView: ImageView? = helper?.getView(R.id.iv_gank_left)
            if (leftImgView != null) {
                Glide.with(mContext)
                    .load(item?.t?.images?.get(0))
                    .into(leftImgView)
            }
            helper?.setGone(R.id.iv_gank_center, false)
            helper?.setGone(R.id.iv_gank_right, false)
        }
    }
}