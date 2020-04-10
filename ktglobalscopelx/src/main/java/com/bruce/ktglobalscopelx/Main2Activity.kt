package com.bruce.ktglobalscopelx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bruce.ktglobalscopelx.R
import kotlinx.coroutines.*

class Main2Activity : BaseCoroutineScopeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

}
