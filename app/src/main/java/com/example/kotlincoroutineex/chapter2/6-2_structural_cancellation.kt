package com.example.kotlincoroutineex.chapter2

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description: 子协程如果不配合取消，在被调用了 cancel() 之后不去结束自己的流程，还有一个副作用，就是它会拖住父协程，让父协程也结束不了
 */
fun main() = runBlocking {

    val job = launch(Dispatchers.Default) {
        var count = 0

        launch {
            while (true) {
                println("child coroutine, parent: ${coroutineContext[Job]!!.parent!!.isActive}, self: $isActive")
            }
        }

        while (true) {
            delay(500)

            count++
            println("count: $count")
        }
    }

    delay(1000)
    job.cancel()
}



