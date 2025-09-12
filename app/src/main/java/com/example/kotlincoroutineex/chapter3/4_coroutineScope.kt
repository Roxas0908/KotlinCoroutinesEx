package com.example.kotlincoroutineex.chapter3

import kotlinx.coroutines.CoroutineScope
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
    val scope = CoroutineScope(EmptyCoroutineContext)

    scope.launch {
        val name = try {
            coroutineScope {
                val deferred1 = async { "roxas" }
                val deferred2 = async { throw RuntimeException("Error!") }

                "${deferred1.await()} ${deferred2.await()}"
            }

        } catch (e: Exception) {
            e.message
        }

        println(name)
    }

    delay(10000)
}

private suspend fun someFun() {
    coroutineScope {
        launch {
//            someCode1()
//            someCode2()
        }
    }

    val scope = CoroutineScope(EmptyCoroutineContext)

    scope.launch {
//        someCode1()
//        someCode2()
    }
}

private suspend fun someFun2() = coroutineScope {
    launch {

    }
}






