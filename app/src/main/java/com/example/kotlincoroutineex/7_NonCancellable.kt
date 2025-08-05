package com.example.kotlincoroutineex

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.isActive
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.time.measureTime


/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
fun main() = runBlocking {
    var childJob: Job? = null
    val scope = CoroutineScope(EmptyCoroutineContext)

    val parentJob = scope.launch {
        childJob = launch(NonCancellable) {
            println("Child started")
            if (isActive) {
                //
                withContext(NonCancellable) {
                    delay(1000)
                }
                throw CancellationException()
            }
            try {
                delay(3000)
            } catch (e: CancellationException) {
                //

                throw e
            }
            println("Child stopped")
        }
        println("childJob parent: ${childJob?.parent}")
        launch(Job()) {
            println("Child 2 started")
            delay(3000)
            println("Child 2 stopped")
        }
        println("Parent started")
        delay(3000)
        println("Parent stopped")
    }
    delay(1500)
    parentJob.cancel()
    NonCancellable.cancel()

    delay(10000)
}


suspend fun writeInfo() = withContext(Dispatchers.IO + NonCancellable) {
    // write to file
    // read from database (Room)
    // write data to file
}


