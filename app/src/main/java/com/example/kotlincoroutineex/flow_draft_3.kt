package com.example.kotlincoroutineex


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.chunked
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.coroutines.EmptyCoroutineContext


/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
fun main() = runBlocking {
    println("主协程开始，线程: ${Thread.currentThread()}")

    launch {
        println("协程1 开始，线程: ${Thread.currentThread()}")
        delay(3000)
        println("协程1 结束，线程: ${Thread.currentThread()}")
    }

    launch {
        println("协程2 开始，线程: ${Thread.currentThread()}")
        for (i in 1..5) {
            println("协程2 正在工作: $i，线程: ${Thread.currentThread()}")
            delay(500)
        }
        println("协程2 结束，线程: ${Thread.currentThread()}")
    }

    println("主协程结束（但会等待子协程）")
}
