package com.bruce.sx.weight.loadCallBack

import com.bruce.sx.R
import com.kingja.loadsir.callback.Callback


/**
 * Description:TODO
 * Create Time:2017/9/4 10:22
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

class EmptyCallback : Callback() {
    override fun onCreateView(): Int {
        return R.layout.layout_empty
    }

}
