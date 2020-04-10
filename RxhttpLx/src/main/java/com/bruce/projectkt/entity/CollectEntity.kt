package com.bruce.projectkt.entity

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.projectkt.entity
 * @description:
 * @date: 2020/4/7
 * @time:  17:55
 */
class CollectEntity {
    /**
     * author : xiaoyang
     * chapterId : 440
     * chapterName : 官方
     * courseId : 13
     * desc :
     *
     *很久以前有Activity.onResume就是界面可见的说法，这种说法毫无疑问是不准确的。
     *
     * 问题是：
     *
     *  1. Activity.onCreate 和 Activity.onResume，在调用时间上有差别么？可以从Message调度去考虑。
     *  1. 有没有一个合理的时机，让我们认为Activity 界面可见了？
     *
     * envelopePic :
     * id : 120115
     * link : https://wanandroid.com/wenda/show/12175
     * niceDate : 2小时前
     * origin :
     * originId : 12175
     * publishTime : 1583732093000
     * title : 每日一问 | 很久以前有Activity.onResume就是界面可见的说法，这种说法错了多少？
     * userId : 36628
     * visible : 0
     * zan : 0
     */

    var author: String? = null
    var chapterId: Int = 0
    var chapterName: String? = null
    var courseId: Int = 0
    var desc: String? = null
    var envelopePic: String? = null
    var id: Int = 0
    var link: String? = null
    var niceDate: String? = null
    var origin: String? = null
    var originId: Int = 0
    var publishTime: Long = 0
    var title: String? = null
    var userId: Int = 0
    var visible: Int = 0
    var zan: Int = 0
}