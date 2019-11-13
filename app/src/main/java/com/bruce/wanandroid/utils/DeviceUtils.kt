package com.bruce.wanandroid.utils

import android.content.Context

fun getScreenWidth(context: Context) : Int {
    return context.resources.displayMetrics.widthPixels
}