package com.example.kotlincoroutineex.chapter1

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * author: liumingsong
 * created on: 2025/6/14 22:08
 * description:
 */
class WithContextActivity: ComponentActivity() {

    private lateinit var infoTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            println("CoroutineScope(Dispatchers.Main).launch(): ${Thread.currentThread()}")

            /**
             * 两个 withContext() 的线程名有时候是不一样的, 有时候是一样的
             */
            val data = withContext(Dispatchers.IO) {
                println("withContext(Dispatchers.IO): ${Thread.currentThread()}")
                // 网络代码
                "data"
            }

            val processData = withContext(Dispatchers.Default) {
                println("withContext(Dispatchers.Default): ${Thread.currentThread()}")
                // 处理数据
                "process $data"
            }

            println("Processed data: $processData")
        }
    }
    


    class MyViewModel: ViewModel() {
        fun someFun() {
            viewModelScope.launch {

            }
        }
    }

}
