package com.example.kotlincoroutineex

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread
import kotlin.coroutines.EmptyCoroutineContext


/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
fun main() = runBlocking {
    Thread.setDefaultUncaughtExceptionHandler { t, e ->
        println("Caught default: $e")
    }
//
//    val thread = Thread() {
//        throw RuntimeException()
//    }
//
//    thread.setUncaughtExceptionHandler { t, e ->
//        println(e)
//    }
//    thread.start()

    val scope = CoroutineScope(EmptyCoroutineContext)
    val handler = CoroutineExceptionHandler { coroutineContext, e ->
        println("Caught in Coroutine: $e")
    }

    scope.launch(handler) {
        launch {
            throw RuntimeException("Error!")
        }
        launch {

        }
    }

    delay(10000)
}



