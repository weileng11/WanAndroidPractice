package com.bruce.projectkt.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.rxLifeScope
import com.bruce.projectkt.R
import com.bruce.projectkt.entity.ArticleEntity
import com.bruce.projectkt.entity.CollectEntity
import com.bruce.projectkt.entity.PageList
import com.bruce.projectkt.entity.Response
import com.bruce.projectkt.url.ElseUrls
import com.bruce.sx.entity.UserEntity
import com.rxjava.rxlife.RxLife
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.async
import rxhttp.await
import rxhttp.awaitOkResponse
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.awaitResponse

/**
 * @author:bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.projectkt.main
 * @description: https://juejin.im/post/5e77604fe51d4527066eb81a#heading-9
 * @date: 2020/4/10
 * @time:  11:22
 */
class MainKtTestAct : AppCompatActivity(), View.OnClickListener {

//    val map= mutableMapOf("username" to "13828821554","password" to "a123456")
    private val map :MutableMap<String,String> = mutableMapOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btArticle.setOnClickListener(this)
        btCollect.setOnClickListener(this)
        map["username"] = "13828821554"
        map.put("password","a123456")
    }

    override  fun onClick(v: View?) {
        when(v?.id){
             R.id.btArticle ->sendbtArticleForm()
             R.id.btCollect->sendbtCollectForm()
        }
    }

    fun sendbtCollectForm(){
        rxLifeScope.launch({
            val collectEntity=getCollectEntity()
            Log.i("info","用户===xxx"+collectEntity.toString())
        },{
            Log.i("info","用户===xxx"+it.cause)
            Log.i("info","用户--"+it.message)
            Toast.makeText(this, "发送失败,请稍后再试", Toast.LENGTH_SHORT).show()
        })
    }
    //测试协程
     fun sendbtArticleForm(){
//        rxLifeScope.launch {
//            val students = RxHttp.get(ElseUrls.Article)
//                .await<PageList<ArticleEntity>>()
//        }

        val job=rxLifeScope.launch({
            //当前运行在协程中，且在主线程运行
            Toast.makeText(this@MainKtTestAct, "成功", Toast.LENGTH_SHORT).show()
            val student = getUser2()
            val personList = getArticle()

//            Log.i("info","用户数据"+student.toString())
//            Log.i("info","用户数据"+student.nickname)
            Log.i("info","用户数据2"+student.data?.nickname)
            for (item in personList.datas!!) {
                Log.i("info","首页数据"+item.audit)
                Log.i("info","首页数据==="+personList.datas)
            }

        }, {
//            it.message
//            it.cause
            //出现异常，就会到这里，这里的it为Throwable类型
            Toast.makeText(this, "发送失败,请稍后再试", Toast.LENGTH_SHORT).show()
        })

//        job.cancel()

    }

//    //当前环境在Fragment中
//    fun getStudent() {
//        //rxLifeScope是RxLife 2.0中，需要单独依赖
//        rxLifeScope.launch({    //通过launch方法开启一个协程
//            val student = RxHttp.get("/service/...")
//                .awaitResponse<ArticleEntity>()
//        }, {
//            //异常回调，这里的it为Throwable类型
//            val code = it.code
//            val msg = it.msg
//        })
//    }


    //挂断方法，获取学生信息
    suspend fun getUser2() : Response<UserEntity>{
       return RxHttp.postForm(ElseUrls.UserLg)
//            .add("username","13828821554")
//            .add("password","a123456")
            .addAll(map)
//            .add("key", "value")
//            .addHeader("headKey", "headValue")
            .await<Response<UserEntity>>()//由于方法指明了返回值为Student类型，故直接写await()，否则需要写 await<Student>()
//        student.errorCode
//        student.errorMsg
//        Log.i("info","用户数据"+student.data?.nickname)
    }

    //挂断方法，获取学生信息
    suspend fun getUser(): UserEntity {
        return RxHttp.postForm(ElseUrls.UserLg)
//            .add("username","13828821554")
//            .add("password","a123456")
            .addAll(map)
//            .add("key", "value")
//            .addHeader("headKey", "headValue")
            .awaitResponse()//由于方法指明了返回值为Student类型，故直接写await()，否则需要写 await<Student>()
    }

    //挂断方法，获取家庭成员信息
    suspend fun getArticle(): PageList<ArticleEntity>{
        return RxHttp.get(ElseUrls.Article,0)
//            .add("studentId", "studentId")
            .awaitResponse() //由于方法指明了返回值为List<Person>类型，故直接写await()，否则需要写 await<List<Person>>()
    }

    //挂断方法，获取家庭成员信息
    suspend fun getCollectEntity(): PageList<CollectEntity>{
        return RxHttp.get(ElseUrls.Collect)
//            .add("studentId", "studentId")
            .awaitResponse() //由于方法指明了返回值为List<Person>类型，故直接写await()，否则需要写 await<List<Person>>()
    }



//    //启动协程，发送请求 并行
//    fun sendRequest() {
//        rxLifeScope.launch({
//            //当前运行在协程中，且在主线程运行
//            val asyncBanner = async { getBanners() }    //这里返回Deferred<List<Banner>>对象
//            val asyncPersons = async { getStudents() }  //这里返回Deferred<List<Student>>对象
//            val banners = asyncBanner.await()           //这里返回List<Banner>对象
//            val students = asyncPersons.await()         //这里返回List<Student>对象
//            //开始更新UI
//
//        }, {
//            //出现异常，就会到这里，这里的it为Throwable类型
////            it.show("发送失败,请稍后再试!") //show方法是在Demo中扩展的方法
//        })
//    }
//
//    //挂断方法，获取学生信息
//    suspend fun getBanners(): List<Banner> {
//        return RxHttp.get("/service/...")
//            .add("key", "value")
//            .addHeader("headKey", "headValue")
//            .await() //由于方法指明了返回值为List<Banner>类型，故这里直接调用await()，否则需要写 .await<List<Banner>>()
//    }
//
//    //挂断方法，获取家庭成员信息
//    suspend fun getStudents(): List<Student> {
//        return RxHttp.get("/service/...")
//            .add("key", "value")
//            .await() //由于方法指明了返回值为List<Person>类型，故这里直接调用await()，否则需要写 .await<List<Person>>()
//    }
}

