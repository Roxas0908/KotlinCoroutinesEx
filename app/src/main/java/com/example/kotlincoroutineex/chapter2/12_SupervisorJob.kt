package com.example.kotlincoroutineex.chapter2

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
fun main() = runBlocking {
    // 1️⃣ 创建一个 SupervisorJob，这个 Job 会作为 scope 的父 Job
    val supervisorJob = SupervisorJob()

    // 2️⃣ 创建一个 CoroutineScope，挂上 SupervisorJob
    val scope = CoroutineScope(Dispatchers.Default + supervisorJob)


    launch {
        scope.launch {
            // 子协程1：会抛异常
            launch {
                throw RuntimeException("子1挂了")
            }
        }
        // 子协程2：延迟打印
        launch {
            delay(400)
            println("子2还能跑")
        }

        // 父协程自己也延迟打印
        delay(500)
        println("父协程还能打印")
    }


    // 让 main 不立即退出
    delay(1000)

    println("scope.active = ${scope.coroutineContext[Job]?.isActive}")  // true
}



