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
fun main(): Unit = runBlocking {
    val scope = CoroutineScope(EmptyCoroutineContext)
    val flow1 = flowOf(1, 2, 3, 4, 5)

    val flow2 = flowOf {




    }

    scope.launch {
        flow1.chunked(2).collect {
//            println("$it")
        }
    }

    println("${Thread.currentThread()}")


    /**
     * 两个 launch() 是运行在同一线程上的, 也就是说 两个 launch() 是逻辑上并行的两个协程, 但物理上运行在同一个线程
     */
    scope.launch(Dispatchers.IO) {
        println("scope.launch(): ${Thread.currentThread()}")
        
        flow1.collect {
//            println("collect() Thread: ${Thread.currentThread()}")
//            println("$it")
        }

        scope.launch(Dispatchers.Default) {
            // scope.launch()-2: Thread[#22,DefaultDispatcher-worker-2,5,main]
            println("scope.launch()-2: ${Thread.currentThread()}")
        }

        val data = withContext(Dispatchers.IO) {
            // withContext(Dispatchers.IO): Thread[#21,DefaultDispatcher-worker-1,5,main]
            println("withContext(Dispatchers.IO): ${Thread.currentThread()}")
            // 网络代码
            "data"
        }

        val processData = withContext(Dispatchers.Default) {
            // withContext(Dispatchers.Default): Thread[#22,DefaultDispatcher-worker-2,5,main]
            println("withContext(Dispatchers.Default): ${Thread.currentThread()}")
            // 处理数据
            "process $data"
        }
    }




    delay(8000)
}
