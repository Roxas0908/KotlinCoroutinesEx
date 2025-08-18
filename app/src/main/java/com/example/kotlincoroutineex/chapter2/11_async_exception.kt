package com.example.kotlincoroutineex.chapter2

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.EmptyCoroutineContext


/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
fun main() = runBlocking {
    val scope = CoroutineScope(EmptyCoroutineContext)
    val handler = CoroutineExceptionHandler { coroutineContext, e ->
        println("Caught in Coroutine: $e")
    }

    scope.async {
        val deferred = async {
            delay(1000)
            throw RuntimeException("Error!")
        }
        launch(Job()) {
            try {
//                delay(2000)
//                deferred.await()
            } catch (e: Exception) {
                println("Caught in await: $e")
            }
            deferred.await()
//            try {
//                delay(1000)
//            } catch (e: Exception) {
//                println("Caught in delay: $e")
//            }

        }
//        delay(100)
//        cancel()
    }

    delay(10000)
}



