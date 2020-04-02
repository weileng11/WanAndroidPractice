package com.bruce.sx.adapter

import android.content.Context
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import com.bruce.sx.R
import com.bruce.sx.utils.ColorUtils
import com.bruce.sx.weight.indicator.OnTabClickListener
import com.bruce.sx.weight.indicator.ZsSimplePagerTitleView
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.adapter
 * @description:tab适配器
 * @date: 2020/3/27
 * @time:  17:45
 */
class TabAdapter(tabList: MutableList<String>) : CommonNavigatorAdapter(){

    private var tabList = tabList
    private var onTabClickListener : OnTabClickListener? = null
    fun setOnTabClickListener(onTabClickListener : OnTabClickListener){
        this.onTabClickListener = onTabClickListener
    }

    override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
        //标题初始化
        val simplePagerTitleView = ZsSimplePagerTitleView(context)
        //文字
        simplePagerTitleView.text = tabList[index]
        //大小
        simplePagerTitleView.textSize = 16f
        //间距
        simplePagerTitleView.setPadding(40, 0, 40, 0)
        //正常颜色
        simplePagerTitleView.normalColor = ColorUtils.parseColor(R.color.text_2)
        //选中颜色
        simplePagerTitleView.selectedColor = ColorUtils.parseColor(R.color.theme)
        simplePagerTitleView.setOnClickListener {
            onTabClickListener?.onTabClick(it,index)
        }

        //选中结果后将字体加粗
        simplePagerTitleView.setSelectListener(object : ZsSimplePagerTitleView.SelectListener{
            override fun onSelect(index: Int, totalCount: Int) {
                val tp = simplePagerTitleView.paint
                tp.isFakeBoldText = true
            }

            override fun onDeselected(index: Int, totalCount: Int) {
                val tp = simplePagerTitleView.paint
                tp.isFakeBoldText = false
            }
        })
        return simplePagerTitleView
    }

    override fun getCount(): Int {
        return tabList.size
    }

    //下标动画
    override fun getIndicator(context: Context?): IPagerIndicator {
        val indicator = LinePagerIndicator(context).apply {
            mode = LinePagerIndicator.MODE_EXACTLY
            lineHeight = UIUtil.dip2px(context, 3.0).toFloat()
            lineWidth = UIUtil.dip2px(context, 20.0).toFloat()
            roundRadius = UIUtil.dip2px(context, 3.0).toFloat()
            startInterpolator = AccelerateInterpolator()
            endInterpolator = DecelerateInterpolator(2.0f)
            setColors(ColorUtils.parseColor(R.color.theme))
        }
        return indicator
    }
}