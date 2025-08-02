package com.example.kotlincoroutineex

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.time.measureTime


/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
fun main() = runBlocking {
    val scope = CoroutineScope(EmptyCoroutineContext)
    val parentJob = scope.launch {
        val childJob = launch {
            println("Child job started")

            delay(3000)

            println("Child job finish")
        }
    }
    delay(1000)
    parentJob.cancel()
    measureTime {
        parentJob.join()
    }.also {
        println("Parent job joined in $it")
    }
    delay(10000)
}



