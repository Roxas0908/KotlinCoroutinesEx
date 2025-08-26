package com.example.kotlincoroutineex.chapter2

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
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
    val context = Dispatchers.IO + Job() + Job()
    val scope = CoroutineScope(EmptyCoroutineContext)

    val job = scope.launch {
        this.coroutineContext[Job]
        coroutineContext.job
        coroutineContext[ContinuationInterceptor]
    }

    delay(10000)
}



