//package com.bruce.sx.base
//
//import android.content.Context
//import android.os.Bundle
//import android.os.Handler
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//
//import androidx.annotation.LayoutRes
//import androidx.fragment.app.Fragment
//
//import com.bruce.sx.R
//import com.bruce.sx.annotation.BindEventBus
//import com.bumptech.glide.Glide
//import com.bumptech.glide.request.RequestOptions
//
//
//import org.greenrobot.eventbus.EventBus
//
//import ren.yale.android.retrofitcachelibrx2.util.LogUtil
//
///**
// * Created by L
// * 2018/9/3
// */
//abstract class BaseFragment2 : Fragment() {
//
//    protected var mRoot: View? = null
//    protected var mHandler: Handler
//
//    @get:LayoutRes
//    protected abstract val contentLayoutId: Int
//
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        TAG = this.javaClass.name
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        if (mRoot == null) {
//            val layId = contentLayoutId
//            val root = inflater.inflate(layId, container, false)
//            mRoot = root
//        } else {
//            if (mRoot!!.parent != null) {
//                (mRoot!!.parent as ViewGroup).removeView(mRoot)
//            }
//        }
//        return mRoot
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        if (this.javaClass.isAnnotationPresent(BindEventBus::class.java)) {
//            EventBus.getDefault().unregister(this)
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//    }
//
//
//    protected fun initData() {
//        mHandler = Handler()
//        if (this.javaClass.isAnnotationPresent(BindEventBus::class.java)) {
//            if (EventBus.getDefault().isRegistered(this))
//                return
//
//            EventBus.getDefault().register(this)
//        }
//    }
//
//    companion object {
//
//        protected var TAG: String
//    }
//
//
//}
