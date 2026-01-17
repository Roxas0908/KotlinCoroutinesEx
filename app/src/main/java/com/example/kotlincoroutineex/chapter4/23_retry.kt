package com.example.kotlincoroutineex.chapter4


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.NullPointerException
import kotlin.coroutines.EmptyCoroutineContext



/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
fun main(): Unit = runBlocking {
    val scope = CoroutineScope(EmptyCoroutineContext)
    val flow1 = flowOf(1, 2, 3, 4, 5)
    val flow2 = flow {
        for (i in 1..5) {
            if (i < 3) {
                emit(i)
            } else if (i < 4) {
                throw NullPointerException()
            } else {
                throw RuntimeException()
            }
        }
    }.map {
        it * 2
    }
    .retry(2) {
        it !is NullPointerException
    }

    scope.launch {
        try {
            flow2.collect {
                println("$it")
            }
        } catch (e: Exception) {
            println("Network error: $e")
        }

    }


    delay(8000)
}


