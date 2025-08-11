package com.example.kotlincoroutineex.chapter2

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
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
    val scope = CoroutineScope(EmptyCoroutineContext)
    var childJob: Job? = null
    val parentJob = scope.launch {
        childJob = launch {
            println("Child started")
            delay(3000)
            println("Child done")
        }
        delay(1000)
        throw CancellationException("Wrong user")
    }
    delay(500)
    println("isActive: parent - ${parentJob.isActive}, child - ${childJob?.isActive}")
    println("isCancelled: parent - ${parentJob.isCancelled}, child - ${childJob?.isCancelled}")
    delay(1000)
    println("isActive: parent - ${parentJob.isActive}, child - ${childJob?.isActive}")
    println("isCancelled: parent - ${parentJob.isCancelled}, child - ${childJob?.isCancelled}")
    delay(10000)
}



