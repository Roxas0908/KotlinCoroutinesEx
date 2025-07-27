package com.example.kotlincoroutineex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.Timer
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class CoroutineActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            println("主线程前: ${Thread.currentThread().name}")

            val data = withContext(Dispatchers.IO) {
                println("IO线程: ${Thread.currentThread().name}")
                "从IO拿到的数据"
            }

            println("主线程后: ${Thread.currentThread().name}")
            println("结果是: $data")
        }
    }

    
}
