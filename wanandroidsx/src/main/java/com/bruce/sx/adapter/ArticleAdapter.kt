package com.bruce.sx.adapter

import android.text.Html
import android.text.TextUtils
import android.widget.ImageView
import com.bruce.sx.R
import com.bruce.sx.constants.Constants
import com.bruce.sx.entity.ArticleEntity
import com.bruce.sx.proxy.ImageLoad
import com.bruce.sx.utils.ColorUtils
import com.bruce.sx.weight.OnLimitClickHelper
import com.bruce.sx.weight.OnLimitClickListener
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * 文章适配器
 */
class ArticleAdapter(list:MutableList<ArticleEntity.DatasBean>)
    : BaseMultiItemQuickAdapter<ArticleEntity.DatasBean,
        BaseViewHolder>(list) {

    init {
        addItemType(Constants.ITEM_ARTICLE, R.layout.item_home_article)
        addItemType(Constants.ITEM_ARTICLE_PIC,R.layout.item_project)
    }

    private var collectClickListener: OnCollectClickListener? = null

    fun setCollectClickListener(collectClickListener:OnCollectClickListener){
        this.collectClickListener = collectClickListener
    }
    //let,with,run,apply,also函数区别 https://blog.csdn.net/u013064109/article/details/78786646
    override fun convert(helper: BaseViewHolder, item: ArticleEntity.DatasBean?) {
        when(helper.itemViewType){
            //不带图片
            Constants.ITEM_ARTICLE ->{
                item?.run {
                    if (type==1){
                        helper.setText(R.id.tvTag,"置顶 ")
                        helper.setTextColor(R.id.tvTag, ColorUtils.parseColor(R.color.red))
                    }else{
                        helper.setText(R.id.tvTag,"")
                    }
                    helper.setText(R.id.tvAuthor,if (!TextUtils.isEmpty(author))author else shareUser)
                    helper.setText(R.id.tvDate,niceDate)
                    helper.setText(R.id.tvTitle, Html.fromHtml(title))
                    helper.setText(R.id.tvChapterName,superChapterName)
                    helper.getView<ImageView>(R.id.ivCollect)
                        .apply {
                            setOnClickListener(OnLimitClickHelper(OnLimitClickListener {
                                collectClickListener?.onCollectClick(helper, helper.adapterPosition)
                            }))
                            if (item.collect) {
                                setImageResource(R.mipmap.article_collect)
                            }else{
                                setImageResource(R.mipmap.article_un_collect)
                            }
                        }
                }
            }

            //带图片
            Constants.ITEM_ARTICLE_PIC->{
                item?.apply {
                    ImageLoad.loadRadius(helper.getView(R.id.ivTitle),envelopePic,20)
                    helper.setText(R.id.tvTitle,title)
                    helper.setText(R.id.tvDes,desc)
                    helper.setText(R.id.tvNameData,"$niceDate | $author")
                    helper.getView<ImageView>(R.id.ivCollect).apply {
                        setOnClickListener(OnLimitClickHelper(OnLimitClickListener {
                            collectClickListener?.onCollectClick(helper, helper.adapterPosition)
                        }))
                        if (item.collect) {
                            setImageResource(R.mipmap.article_collect)
                        }else{
                            setImageResource(R.mipmap.article_un_collect)
                        }
                    }
                }
            }

        }
    }
}