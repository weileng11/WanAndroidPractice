package com.bruce.wanandroidmvvm_sx.app.weight.loadCallBack

import com.bruce.wanandroidmvvm_sx.R
import com.kingja.loadsir.callback.Callback


class EmptyCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_empty
    }

}
