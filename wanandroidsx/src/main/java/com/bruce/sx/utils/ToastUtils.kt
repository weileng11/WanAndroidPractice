package com.bruce.sx.utils

import android.widget.Toast

import com.bruce.sx.WanAndroidApplication

object ToastUtils {

    fun show(msg: String) {
        Toast.makeText(WanAndroidApplication.context, msg, Toast.LENGTH_SHORT).show()
    }
}
