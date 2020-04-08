package com.bruce.projectkt.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bruce.projectkt.OnError;
import com.bruce.projectkt.R;
import com.bruce.projectkt.entity.ArticleEntity;
import com.bruce.projectkt.entity.BannerEntity;
import com.bruce.projectkt.entity.CollectEntity;
import com.bruce.projectkt.entity.IntegralEntity;
import com.bruce.projectkt.entity.IntegralRecordEntity;
import com.bruce.projectkt.entity.NavigationEntity;
import com.bruce.projectkt.entity.RankEntity;
import com.bruce.projectkt.url.ElseUrls;
import com.bruce.sx.entity.TabEntity;
import com.bruce.sx.entity.UserEntity;
import com.google.gson.Gson;
import com.rxjava.rxlife.RxLife;

import rxhttp.wrapper.param.RxHttp;

/**
 * @author: MainTestAct
 * @project: WanAndroidPractice
 * @package: com.bruce.projectkt.main
 * @description:
 * @date: 2020/4/8
 * @time: 10:04
 */
public class MainTestAct extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btArticle).setOnClickListener(this);
        findViewById(R.id.btBanner).setOnClickListener(this);

        findViewById(R.id.btCollect).setOnClickListener(this);
        findViewById(R.id.btIntegral).setOnClickListener(this);
        findViewById(R.id.btIntegralRecord).setOnClickListener(this);
        findViewById(R.id.btNavigation).setOnClickListener(this);
        findViewById(R.id.btRank).setOnClickListener(this);
        findViewById(R.id.btTab).setOnClickListener(this);
        findViewById(R.id.btUser).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btArticle:
                sendbtArticleForm();
                break;
            case R.id.btBanner:
                sendbtBannerForm();
                break;
            case R.id.btCollect:
                sendbtCollectForm();
                break;
            case R.id.btIntegral:
                sendbtIntegralForm();
                break;

            case R.id.btIntegralRecord:
                sendbtIntegralRecordForm();
                break;
            case R.id.btNavigation:
                sendbtNavigationForm();
                break;
            case R.id.btRank:
                sendbtRankForm();
                break;
            case R.id.btTab:
                sendbtTabForm();
                break;
            case R.id.btUser:
                sendbtUserForm();
                break;
        }
    }

    //发送Post表单请求,根据关键字查询文章
    public void sendbtArticleForm() {
        RxHttp.get(ElseUrls.Article)
                .asResponsePageList(ArticleEntity.class)
                .as(RxLife.asOnMain(this))  //感知生命周期，并在主线程回调
                .subscribe(pageList -> {
                    Toast.makeText(this,new Gson().toJson(pageList),Toast.LENGTH_LONG).show();
                    //成功回调
                }, (OnError) error -> {
                    //失败回调
                    error.show("发送失败,请稍后再试!");
                });
    }

    //发送Post表单请求,根据关键字查询文章无分页()
    public void sendbtBannerForm() {
        RxHttp.get(ElseUrls.Banner)
                .asResponseList(BannerEntity.class)
                .as(RxLife.asOnMain(this))  //感知生命周期，并在主线程回调
                .subscribe(bannerEntity -> {
                    Toast.makeText(this,new Gson().toJson(bannerEntity),Toast.LENGTH_LONG).show();
                    //成功回调
                }, (OnError) error -> {
                    //失败回调
                    error.show("发送失败,请稍后再试!");
                });
    }

    //发送Post表单请求,根据关键字查询文章无分页()
    public void sendbtCollectForm() {
        RxHttp.get(ElseUrls.Collect)
                .asResponsePageList(CollectEntity.class)
                .as(RxLife.asOnMain(this))  //感知生命周期，并在主线程回调
                .subscribe(collectEntityPageList -> {
                    Toast.makeText(this,new Gson().toJson(collectEntityPageList),Toast.LENGTH_LONG).show();
                    //成功回调
                }, (OnError) error -> {
                    //失败回调
                    Log.i("info",error.getErrorMsg());
                    Log.i("info",String.valueOf(error.getErrorCode()));
                    error.show("发送失败,请稍后再试!");
                });
    }

    //发送Post表单请求,根据关键字查询文章无分页()
    public void sendbtIntegralForm() {
        RxHttp.get(ElseUrls.Intergral)
                .asResponse(IntegralEntity.class)
                .as(RxLife.asOnMain(this))  //感知生命周期，并在主线程回调
                .subscribe(integralEntity -> {
                    Toast.makeText(this,new Gson().toJson(integralEntity),Toast.LENGTH_LONG).show();
                    //成功回调
                }, (OnError) error -> {
                    //失败回调
                    Log.i("info",error.getErrorMsg());
                    error.show("发送失败,请稍后再试!");
                });
    }

    //发送Post表单请求,根据关键字查询文章无分页()
    public void sendbtIntegralRecordForm() {
        RxHttp.get(ElseUrls.IntegralRecord)
                .asResponse(IntegralRecordEntity.class)
                .as(RxLife.asOnMain(this))  //感知生命周期，并在主线程回调
                .subscribe(integralRecordEntity -> {
                    Toast.makeText(this,new Gson().toJson(integralRecordEntity),Toast.LENGTH_LONG).show();
                    //成功回调
                }, (OnError) error -> {
                    //失败回调
                    error.show("发送失败,请稍后再试!");
                    Log.i("info",error.getErrorMsg());
                });
    }

    //发送Post表单请求,根据关键字查询文章无分页()
    public void sendbtNavigationForm() {
        RxHttp.get(ElseUrls.Navigation)
                .asResponseList(NavigationEntity.class)
                .as(RxLife.asOnMain(this))  //感知生命周期，并在主线程回调
                .subscribe(navigationEntityList -> {
                    Toast.makeText(this,new Gson().toJson(navigationEntityList),Toast.LENGTH_LONG).show();
                    //成功回调
                }, (OnError) error -> {
                    //失败回调
                    error.show("发送失败,请稍后再试!");
                    Log.i("info",error.getErrorMsg());
                });
    }

    //发送Post表单请求,根据关键字查询文章无分页()
    public void sendbtRankForm() {
        RxHttp.get(ElseUrls.Rank)
                .asResponsePageList(RankEntity.class)
                .as(RxLife.asOnMain(this))  //感知生命周期，并在主线程回调
                .subscribe(rankEntityPageList -> {
                    Toast.makeText(this,new Gson().toJson(rankEntityPageList),Toast.LENGTH_LONG).show();
                    //成功回调
                }, (OnError) error -> {
                    //失败回调
                    error.show("发送失败,请稍后再试!");
                });
    }


    //发送Post表单请求,根据关键字查询文章无分页()
    public void sendbtTabForm() {
        RxHttp.get(ElseUrls.Tab)
                .asResponseList(TabEntity.class)
                .as(RxLife.asOnMain(this))  //感知生命周期，并在主线程回调
                .subscribe(tabEntityList -> {
                    Toast.makeText(this,new Gson().toJson(tabEntityList),Toast.LENGTH_LONG).show();
                    //成功回调
                }, (OnError) error -> {
                    //失败回调
                    error.show("发送失败,请稍后再试!");
                });
    }

    //发送Post表单请求,根据关键字查询文章无分页()
    public void sendbtUserForm() {
        RxHttp.postForm(ElseUrls.UserLg)
                .add("username","13828821554")
                .add("password","a123456")
                .asResponse(UserEntity.class)
                .as(RxLife.asOnMain(this))  //感知生命周期，并在主线程回调
                .subscribe(userEntity -> {
                    Toast.makeText(this,new Gson().toJson(userEntity),Toast.LENGTH_LONG).show();
                    sendbtIntegralForm();
                    //成功回调
                }, (OnError) error -> {
                    //失败回调
                    error.show("发送失败,请稍后再试!");
                });
    }
}
