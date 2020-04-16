package com.bruce.databing.view.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bruce.databing.R;
import com.bruce.databing.databinding.ActivityMainBinding;

/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.databing.view.activity
 * @description: https://blog.csdn.net/mq2553299/article/details/72713551
 * @date: 2020/4/16
 * @time: 10:35
 */
public class A01MainActivity extends AppCompatActivity {


    public Presenter presenter;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject();
    }

    private void inject() {
        //现在我们通过DataBindingUtil设置布局文件
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //初始化Presenter对象
        presenter = new Presenter();
        //将presenter对象赋予XML中的 data -> variable -> presenter
        binding.setPresenter(presenter);
    }

    public class Presenter {

        public String message = " ~122222222 ";

        public void baseDataBinding() {
            startActivity(new Intent(A01MainActivity.this,A02DataBindingBaseActivity.class));
        }

        public void recyclerView(){
            startActivity(new Intent(A01MainActivity.this,A03RecyclerBindActivity.class));
        }
//
        public void mulTypeRecyclerView(){
            startActivity(new Intent(A01MainActivity.this,A04MulTypeRecyclerBindActivity.class));
        }
//
        public void mvvm(){
            startActivity(new Intent(A01MainActivity.this,A06MvvmActivity.class));
        }
//
        public void studyLibrary(){
            startActivity(new Intent(A01MainActivity.this,A05MultiTypeStudyActivity.class));
        }
    }
}
