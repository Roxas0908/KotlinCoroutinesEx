package com.example.kotlincoroutineex.chapter5


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.onTimeout
import kotlinx.coroutines.selects.select
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.concurrent.thread
import kotlin.coroutines.EmptyCoroutineContext



/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
fun main(): Unit = runBlocking {
    val scope = CoroutineScope(EmptyCoroutineContext)
    val lock = Any()
    var number = 0
    val mutex = Mutex()

    val job1 = scope.launch {
        repeat(100_000) {
            mutex.withLock {
                number++
            }
        }
    }
    val job2 = scope.launch {
        repeat(100_000) {
            mutex.withLock {
                number--
            }
        }
    }

    job1.join()
    job2.join()

    println("number: $number")


    delay(8000)
}




