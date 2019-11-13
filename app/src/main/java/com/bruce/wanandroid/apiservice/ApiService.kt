package com.bruce.wanandroid.apiservice

import com.bruce.wanandroid.base.BaseResponse
import com.bruce.wanandroid.db.bean.User
import com.bruce.wanandroid.gank.bean.GankToday
import com.bruce.wanandroid.gank.bean.WxPublic
import com.bruce.wanandroid.home.bean.Article
import com.bruce.wanandroid.home.bean.ArticleResponse
import com.bruce.wanandroid.home.bean.Banner
import com.bruce.wanandroid.meizi.bean.Meizi
import com.bruce.wanandroid.project.bean.ProjectResponse
import com.bruce.wanandroid.project.bean.ProjectTab
import com.bruce.wanandroid.search.bean.SearchHot
import com.bruce.wanandroid.search.bean.SearchResultResponse
import com.bruce.wanandroid.setting.bean.LogoutResult
import com.bruce.wanandroid.system.bean.SystemCategory
import com.bruce.wanandroid.user.bean.RegisterResponse
import com.bruce.wanandroid.web.bean.AddFavoriteResponse
import com.tencent.mm.opensdk.modelbase.BaseResp
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {


    @GET("project/tree/json")
    fun getProjectTabs(): Observable<BaseResponse<List<ProjectTab>>>

    @GET("project/list/{page}/json")
    fun getProjectLists(@Path("page") page: Int, @Query("cid") cid: Int): Observable<BaseResponse<ProjectResponse>>

    @GET("banner/json")
    fun getBanner(): Observable<BaseResponse<List<Banner>>>

    @GET("article/top/json")
    fun getTopArticle(): Observable<BaseResponse<List<Article>>>

    @GET("article/list/{page}/json")
    fun getArticles(@Path("page") page: Int): Observable<BaseResponse<ArticleResponse>>

    @GET("hotkey/json")
    fun getSearchHot(): Observable<BaseResponse<ArrayList<SearchHot>>>

    @POST("article/query/{page}/json")
    @FormUrlEncoded
    fun getSearchResult(@Path("page") page: Int, @Field("k") keyword: String): Observable<BaseResponse<SearchResultResponse>>

    @GET("http://gank.io/api/data/福利/{pageSize}/{page}")
    fun getMeiziList(@Path("pageSize") pageSize: Int, @Path("page") page: Int): Observable<BaseResponse<List<Meizi>>>

    @POST("lg/collect/{id}/json")
    fun addFavorite(@Path("id") id: Int): Observable<BaseResponse<AddFavoriteResponse>>


    @POST("lg/collect/add/json")
    @FormUrlEncoded
    fun addFavorite(@Field("title") title: String, @Field("author") author: String, @Field("link") link: String): Observable<BaseResponse<AddFavoriteResponse>>

    @POST("user/login")
    @FormUrlEncoded
    fun login(@Field("username") username: String, @Field("password") password: String): Observable<BaseResponse<User>>

    @POST("user/register")
    @FormUrlEncoded
    fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): Observable<BaseResponse<RegisterResponse>>


    @GET("tree/json")
    fun getSystem(): Observable<BaseResponse<List<SystemCategory>>>

    @GET("article/list/{page}/json")
    fun getSystemArticles(@Path("page") page: Int, @Query("cid") cid: Int): Observable<BaseResponse<ArticleResponse>>

    @GET("lg/collect/list/{page}/json")
    fun getArticleFavorites(@Path("page") page: Int): Observable<BaseResponse<ArticleResponse>>

    @GET("wxarticle/chapters/json")
    fun getWxPublic(): Observable<BaseResponse<List<WxPublic>>>


    @GET("http://gank.io/api/today")
    fun getGankToday(): Observable<BaseResponse<HashMap<String, List<GankToday>>>>

    @GET("wxarticle/list/{id}/{page}/json")
    fun getWxPublicArticle(@Path("id") id: Int, @Path("page") page: Int): Observable<BaseResponse<ArticleResponse>>


    @GET("user/logout/json")
    fun logout(): Observable<BaseResponse<LogoutResult>>


}