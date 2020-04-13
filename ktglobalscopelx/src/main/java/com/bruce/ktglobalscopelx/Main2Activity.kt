package com.bruce.ktglobalscopelx

import android.os.Bundle
import com.orhanobut.logger.Logger

class Main2Activity : BaseCoroutineScopeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        Logger.e("222222")
    }

}
