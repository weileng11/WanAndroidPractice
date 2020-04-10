//package com.bruce.projectkt.main
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.View
//import com.bruce.projectkt.OnError
//import com.bruce.projectkt.R
//import com.bruce.projectkt.entity.ArticleEntity
//import com.bruce.projectkt.entity.PageList
//import com.bruce.projectkt.url.ElseUrls.Article
//import com.google.gson.Gson
//import com.rxjava.rxlife.ObservableLife
//import com.rxjava.rxlife.RxLife
//import kotlinx.android.synthetic.main.activity_main.*
//import okhttp3.internal.http2.ErrorCode
//import rxhttp.wrapper.param.RxHttp
//
//class MainActivity : AppCompatActivity(),View.OnClickListener {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        btArticle.setOnClickListener(this)
//        btBanner.setOnClickListener(this)
//    }
//
//    override fun onClick(p0: View?){
//          when(p0?.id){
////              R.id.btArticle->sendArticleForm()
//          }
//    }
//
//    //发送Post表单请求,根据关键字查询文章
//    fun sendPostForm(view: View) {
//        RxHttp.postForm("/article/query/0/json")
//            .add("k", "性能优化")
//            .asResponsePageList(ArticleEntity::class.java)
//            .`as`<ObservableLife<PageList<ArticleEntity>>>(RxLife.asOnMain(this))  //感知生命周期，并在主线程回调
//            .subscribe({ pageList ->
//                //成功回调
//            }, { error ->
//
//            } as OnError)
//    }
//}
