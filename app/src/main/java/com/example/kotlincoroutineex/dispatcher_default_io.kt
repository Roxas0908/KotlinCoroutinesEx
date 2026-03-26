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
 * io、default 实际底层共用一个线程池，但 io 任务多了并不会挤占到 default 的线程数，这是 io 不同于 default 的调度规则
 */
fun main(): Unit = runBlocking {
    launch(Dispatchers.Default) {          // ① 先在 Default 上启动
        println("① 当前线程: ${Thread.currentThread().name}")

        withContext(Dispatchers.IO) {      // ② 切换到 IO
            println("② 切换到 IO 后的线程: ${Thread.currentThread().name}")

            Thread.sleep(1000)             // ③ 故意做个阻塞 I/O 操作
            println("③ 阻塞 1 秒后线程依然是: ${Thread.currentThread().name}")
        }

        println("④ 回到 Default 后的线程: ${Thread.currentThread().name}")
    }
}
