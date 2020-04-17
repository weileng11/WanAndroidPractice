package com.bruce.sx.http

import com.bruce.sx.entity.*
import io.reactivex.Observable
import ren.yale.android.retrofitcachelibrx2.anno.Cache
import ren.yale.android.retrofitcachelibrx2.anno.Mock
import retrofit2.http.*
import java.util.concurrent.TimeUnit

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.http
 * @description: https://www.cnblogs.com/weizhxa/p/9875707.html
 * 缓存说明:https://github.com/yale8848/RetrofitCache
 * @Cache(time = 20) 缓存设置为20秒
 * @Cache(time = 20,timeUnit = TimeUnit.MINUTES) 缓存设置为20分钟
 * @Cache() 默认时间缓存,默认是0秒
 * @Cache(forceCacheNoNet = false) 默认在无网的时候强制走缓存，forceCacheNoNet=false时无网络时不强制缓存
 * @Mock(value = "{\"data\":\"mockdata\"}") //模拟内存数据 添加模拟数据（value,assets,url同时都配置的话，就按照这个顺序处理）
 * @Mock(assets = "mock/mock.json") //从assets获取模拟数据
 * @Mock(url = "http://url.com/test") //从新的url请求数据
 * @date: 2020/3/26
 * @time:  17:56
 */
interface ApiService {
    /**
     * 获取首页文章数据
     */
    @Cache(time = 10) //缓存设置为10秒
    @GET("/article/list/{page}/json")
    fun getHomeList(@Path("page") pageNo: Int): Observable<BaseResponse<ArticleEntity>>

    /**
     * 获取首页置顶文章数据
     */
    @Cache(time = 20, timeUnit=TimeUnit.MINUTES) //缓存设置为20分钟
    @GET("/article/top/json")
    fun getTopList(): Observable<BaseResponse<MutableList<ArticleEntity.DatasBean>>>

    /**
     * banner
     */
    @Cache //默认时间缓存,默认是0秒
    @GET("/banner/json")
    fun getBanner(): Observable<BaseResponse<MutableList<BannerEntity>>>

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("/user/login")
    fun login(@Field("username") username: String,
              @Field("password") password: String): Observable<BaseResponse<UserEntity>>

    @GET("/user/logout/json")
    fun logout():Observable<BaseResponse<Any>>

    /**
     * 获取收藏文章数据
     */
    @GET("/lg/collect/list/{page}/json")
    fun getCollectData(@Path("page") pageNo: Int):
            Observable<BaseResponse<CollectEntity>>

    /**
     * 获取个人积分
     */
    @GET("/lg/coin/userinfo/json")
    fun getIntegral():Observable<BaseResponse<IntegralEntity>>

    /**
     * 收藏
     */
    @POST("/lg/collect/{id}/json")
    fun collect(@Path("id")id:Int):Observable<BaseResponse<Any>>

    /**
     * 取消收藏
     */
    @POST("/lg/uncollect_originId/{id}/json")
    fun unCollect(@Path("id") id: Int): Observable<BaseResponse<Any>>

    /**
     * 获取项目tab
     */
    @GET("/project/tree/json")
    fun getProjectTabList(): Observable<BaseResponse<MutableList<TabEntity>>>

    /**
     * 获取项目tab
     */
    @GET("/wxarticle/chapters/json  ")
    fun getAccountTabList(): Observable<BaseResponse<MutableList<TabEntity>>>

    /**
     * 获取项目列表
     */
    @GET("/project/list/{pageNum}/json")
    fun getProjectList(@Path("pageNum")pageNum:Int,@Query("cid")cid:Int)
            : Observable<BaseResponse<ArticleEntity>>

    /**
     * 获取公众号列表
     */
    @GET("/wxarticle/list/{id}/{pageNum}/json")
    fun getAccountList(@Path("id")cid:Int,@Path("pageNum")pageNum:Int)
            : Observable<BaseResponse<ArticleEntity>>


    /**
     * 获取项目tab
     */
    @POST("/article/query/{pageNum}/json")
    fun search(@Path("pageNum")pageNum:Int,@Query("k")k:String)
            : Observable<BaseResponse<ArticleEntity>>

    /**
     * 体系
     */
    @GET("/tree/json")
    fun getSystemList() : Observable<BaseResponse<MutableList<SystemListEntity>>>


    /**
     * 获取项目tab
     */
    @GET("/article/list/{pageNum}/json")
    fun getSystemArticle(@Path("pageNum")pageNum:Int,@Query("cid")cid:Int)
            : Observable<BaseResponse<ArticleEntity>>

    /**
     * 导航
     */
    @GET("/navi/json")
    fun getNavigation() : Observable<BaseResponse<MutableList<NavigationEntity>>>

    /**
     * 排名
     */
    @GET("/coin/rank/{pageNum}/json")
    fun getRank(@Path("pageNum")pageNum: Int) : Observable<BaseResponse<RankEntity>>

    /**
     * 积分记录
     */
    @GET("/lg/coin/list/{pageNum}/json")
    fun getIntegralRecord(@Path("pageNum")pageNum: Int) : Observable<BaseResponse<IntegralRecordEntity>>

    /**
     * 我分享的文章
     */
    @GET("/user/lg/private_articles/{pageNum}/json")
    fun getMyArticle(@Path("pageNum")pageNum: Int) : Observable<BaseResponse<MyArticleEntity>>

    /**
     * 我分享的文章
     */
    @POST("/lg/user_article/delete/{id}/json")
    fun deleteMyArticle(@Path("id")id: Int) : Observable<BaseResponse<Any>>

    /**
     * 分享文章
     */
    @POST("/lg/user_article/add/json")
    fun shareArticle(@Query("title")title: String,@Query("link")link: String) : Observable<BaseResponse<Any>>

    /**
     * 注册
     */
    @POST("/user/register")
    fun register(@Query("username")username: String,
                 @Query("password")password: String,
                 @Query("repassword")repassword: String) : Observable<BaseResponse<Any>>
}