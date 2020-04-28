package com.bruce.wanandroidmvvm_sx.data.bean


/**
 * @author:
 * @project: WanAndroidPractice
 * @package: com.bruce.wanandroidmvvm_sx.data.bean
 * @description:
 * @date: 2020/4/28
 * @time:  16:17
 */
/*{ "desc" : "享学~",
      "id" : "29",
      "imagePath" : "https://wanandroid.com/blogimgs/cd53b340-1d94-4810-b864-567574e85de7.jpeg",
      "isVisible" : "1",
      "order" : "0",
      "title" : "老板说要加点功能。。。",
      "type" :"0",
      "url" : "https://mp.weixin.qq.com/s/Rv70Tpb4nVizSK0TrK6_FA"
      "datas" : [{"apkLink":"","audit":1,"author":"","canEdit":false,"chapterId":502,"chapterName":"自助","collect":false,"courseId":13,"desc":"","descMd":"","envelopePic":"","fresh":false,"id":12154,"link":"https://juejin.im/post/5e5b50eb6fb9a07cae136773","niceDate":"2020-03-02 09:32","niceShareDate":"2020-03-02 09:32","origin":"","prefix":"","projectLink":"","publishTime":1583112725000,"selfVisible":0,"shareDate":1583112725000,"shareUser":"JsonChao","superChapterId":494,"superChapterName":"广场Tab","tags":[],"title":"【建议收藏】2020年中高级Android大厂面试秘籍，为你保驾护航金三银四，直通大厂","type":0,"userId":611,"visible":1,"zan":0}]
    }*/
data class Test(
    val apkLink: String? = null,
    val audit: Int? = null,
    val author: String? = null,
    val canEdit: Boolean? = null,
    val chapterId: Int? = null,
    val chapterName: String? = null,
    val collect: Boolean? = null,
    val courseId: Int? = null,
    val desc: String? = null,
    val descMd: String? = null,
    val envelopePic: String? = null,
    val fresh: Boolean? = null,
    val id: Int? = null,
    val link: String? = null,
    val niceDate: String? = null,
    val niceShareDate: String? = null,
    val origin: String? = null,
    val prefix: String? = null,
    val projectLink: String? = null,
    val publishTime: Long? = null,
    val selfVisible: Int? = null,
    val shareDate: Long? = null,
    val shareUser: String? = null,
    val superChapterId: Int? = null,
    val superChapterName: String? = null,
    val tags: List<TagsResponse>? = null,
    val title: String? = null,
    val type: Int? = null,
    val userId: Int? = null,
    val visible: Int? = null,
    val zan: Int? = null
)