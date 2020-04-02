package com.bruce.sx.proxy

import android.widget.ImageView
import com.bruce.sx.R
import com.bruce.sx.base.WanAndroidApplication
import com.bruce.sx.weight.GlideRoundTransform
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.proxy
 * @description:Glide处理
 * @date: 2020/3/26
 * @time:  16:08
 */
class ImageLoad {
    companion object {

        /**
         * 默认加载
         */
        fun load(imageView: ImageView, url: String?) {
//            val factory =
//                DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
            url.let {
                Glide.with(imageView.context)
                    .load(url)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    //.placeholder(R.mipmap.image_holder)
                    //.error(R.mipmap.image_holder)
                    .into(imageView)
            }
        }

        /**
         * 加载圆角图片
         * 鉴于今天下午的表现，面对赵老师，我一次又一次的食言，
         * 辜负了您对我的栽培和厚望，于此我感到非常的羞愧。睡醒后本应该和赵老师谈谈情说说爱，不料自己记性太差，
         * 忘记睡前说的话，于此我感到非常懊恼。面对今天的错误，我将作出深刻的检讨，保证以后绝不再犯，如有再犯绝不姑息。
         */
        fun loadRadius(imageView: ImageView, url: String?, round: Int) {

            val factory =
                DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
            url?.let {
                Glide.with(imageView.context)
                    .load(url)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .transform(GlideRoundTransform(imageView.context, round))
                    //.placeholder(R.mipmap.internet_error)
                    .into(imageView)
            }

        }

        /**
         * 加载圆形图片
         */
        fun loadRound(imageView: ImageView, url: String, round: Int) {
            val factory =
                DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
            Glide.with(imageView.context)
                .load(url)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.mipmap.internet_error)
                .transform(RoundedCorners(8))
                .into(imageView)
        }

        /**
         * 清除缓存
         */
        fun clearCache() {
            Glide.get(WanAndroidApplication.context!!).clearMemory()
            System.gc()
        }
    }
}