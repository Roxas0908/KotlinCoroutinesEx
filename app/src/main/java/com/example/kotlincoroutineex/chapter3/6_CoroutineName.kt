package com.example.kotlincoroutineex.chapter3

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.coroutineContext


/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
fun main(): Unit = runBlocking {
    val name = CoroutineName("MyCoroutine")
    val scope = CoroutineScope(name + Dispatchers.IO)

    scope.launch {
        println("CoroutineName: ${coroutineContext[CoroutineName]}")
        println("CoroutineName: ${coroutineContext[CoroutineName]?.name}")
    }


}








