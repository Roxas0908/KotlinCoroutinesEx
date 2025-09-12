package com.example.kotlincoroutineex.chapter3

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.coroutineContext


/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
fun main3() = runBlocking {
    val scope = CoroutineScope(EmptyCoroutineContext)

    scope.launch {
        delay(1000)

        println("Dispatcher: ${coroutineContext[ContinuationInterceptor]}")

        showDispatcher()
        currentCoroutineContext()
    }

    delay(10000)
}

private fun flowFun() {
    flow<String> {
        coroutineContext
    }

    GlobalScope.launch {
        flow<String> {
//            coroutineContext
            currentCoroutineContext()
        }
    }
}

private suspend fun showDispatcher() {
    delay(1000)
    println("Dispatcher: ${coroutineContext[ContinuationInterceptor]}")
}

