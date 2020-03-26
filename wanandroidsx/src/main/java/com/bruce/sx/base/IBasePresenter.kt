package com.bruce.sx.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent


/**
 * @author: bruce
 * @project: WanAndroidPractice
 * @package: com.bruce.sx.base
 * @description:参考文章https://www.cnblogs.com/baiqiantao/p/9614683.html
 * @date: 2020/3/26
 * @time:  11:33
 *
 * LifecycleObserver
 * 生命周期感知组件[Lifecycle-aware components]执行操作[perform actions]以响应另一个组件(例如 Activity 或 Fragment)的生命周期状态的更改。这些组件可帮助您生成更易于组织[better-organized]且通常更轻量级[lighter-weight]的代码，并且这些代码更易于维护。
一种常见的模式是，在 Activity 或 Fragment 的生命周期方法中实现依赖组件的操作[the actions of the dependent components]。 但是，这种模式导致代码组织不良[poor organization]以及错误的增加。通过使用生命周期感知组件，您可以将依赖组件的代码移出生命周期方法、并移入组件本身。
android.arch.lifecycle 包提供了类和接口，使您可以构建生命周期感知组件，这些组件可以根据 Activity 或 Fragment 的当前生命周期状态自动调整其行为。
Android框架中定义的大多数应用程序组件都附加了生命周期。生命周期由操作系统或流程中运行的框架代码所管理。它们是Android工作原理的核心，您的应用程序必须尊重它们。 不这样做可能会触发内存泄漏甚至应用程序崩溃。
android.arch.lifecycle 包提供了类和接口，可帮助您以弹性和隔离的方式[resilient and isolated way]解决这些问题。
 */
interface IBasePresenter<V: IBaseView>: LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate()

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart()

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume()

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause()

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop()

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy()

//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    fun customMethod()
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
//    fun onAny()//此方法可以有参数，但类型必须如两者之一(LifecycleOwner owner,Lifecycle.Event event)

}