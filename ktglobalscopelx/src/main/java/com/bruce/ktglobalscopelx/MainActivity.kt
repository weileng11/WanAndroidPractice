package com.bruce.ktglobalscopelx

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

//https://juejin.im/post/5e78b1145188255df40fab07
class MainActivity : BaseCoroutineScopeActivity() {

    lateinit var job: Job

    val mainViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        global_scope_launch.setOnClickListener {
//            globalScopeLaunch()
            mainScopeLaunch()
        }
        global_async_launch.setOnClickListener {
            globalScopeAsync()
        }

        next_activity.setOnClickListener {
            startActivity(Intent(this, Main2Activity::class.java))
            finish()
        }

        view_model_print.setOnClickListener {
            mainViewModel.LogPrint()//LogPrint1
        }
        view_model_print1.setOnClickListener {
            mainViewModel.LogPrint1()
        }
        view_model_async_print.setOnClickListener {
            mainViewModel.LogAsync()
        }
        catch_fun.setOnClickListener {
            mainViewModel.catchFun()
        }
        catch_fun1.setOnClickListener {
            mainViewModel.catch1Fun()
        }
        catch_fun2.setOnClickListener {
            mainViewModel.catch2Fun()
        }
        catch_async_fun.setOnClickListener {
            mainViewModel.catchasyncFun()
        }
        catch_async_fun2.setOnClickListener {
            mainViewModel.catchasyncFun2()
        }
    }

    //runBlocking会阻塞导致立马弹出~~~这个Toast不会立刻显示出来，而是等了1秒后，再弹出来。
    private fun globalScopeLaunch() {
        job = GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.Main) {
                runBlocking {
                    delay(1000)
                    Toast.makeText(this@MainActivity, "等待一秒弹出", Toast.LENGTH_LONG).show()
                }
            }
            Toast.makeText(this@MainActivity, "立马弹出~~~", Toast.LENGTH_LONG).show()
            delay(5000)
            Toast.makeText(this@MainActivity, "等待五秒弹出~~~", Toast.LENGTH_LONG).show()
        }
    }

    private fun globalScopeAsync() {
        GlobalScope.launch(Main) {
            val deferred = async(IO) {
                Thread.sleep(5000)
                "等待五秒弹出~~~"
            }
            Toast.makeText(this@MainActivity, "立马先弹出来~~", Toast.LENGTH_LONG).show()
            val message = deferred.await()
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
        }
    }

    private fun mainScopeLaunch() {
        val job=launch {
            withContext(Dispatchers.Main) {
                runBlocking {
                    delay(1000)
                    Toast.makeText(this@MainActivity, "等待一秒弹出", Toast.LENGTH_LONG).show()
                }
            }
            Toast.makeText(this@MainActivity, "立马弹出~~~", Toast.LENGTH_LONG).show()
            delay(5000)
            Toast.makeText(this@MainActivity, "等待五秒弹出~~~", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!job.isCancelled) {
            job.cancel()
        }
        cancel()
    }
}
