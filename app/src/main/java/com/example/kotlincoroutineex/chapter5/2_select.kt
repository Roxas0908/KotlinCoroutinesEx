package com.example.kotlincoroutineex.chapter5


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.onTimeout
import kotlinx.coroutines.selects.select
import kotlin.coroutines.EmptyCoroutineContext



/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
fun main(): Unit = runBlocking {
    val scope = CoroutineScope(EmptyCoroutineContext)

    val job1 = launch {
        delay(1000)
        println("job1 done")
    }
    val job2 = launch {
        delay(2000)
        println("job2 done")
    }
    val job3 = async {
        delay(800)
        "async"
    }
    val channel = Channel<String>()

    scope.launch {
        val result = select {
            job1.onJoin {
                delay(1000)
                1
            }
            job2.onJoin {
                2
            }
            job3.onAwait {
                it
            }
//            channel.onSend("haha") {
//                "channel.onSend() done"
//            }
            onTimeout(500) {
                "onTimeout()"
            }
        }

        println("$result")
    }




    delay(8000)
}


