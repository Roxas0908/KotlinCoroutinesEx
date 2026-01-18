package com.example.kotlincoroutineex.chapter4


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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
        try {
            for (i in 1..-1) {
                emit(i)
            }
        } catch (e: Exception) {
            println("try / catch: $e")
        }
    }.onStart {
        println("onStart 1")
//        throw RuntimeException("onStart error")
    }.onCompletion {
        println("onCompletion(): $it")
    }.onEmpty {
        println("onEmpty()")
    }
    .catch {
        println("catch(): $it")
    }
    .onStart {
        println("onStart 2")
    }

    scope.launch {
        flow2.collect {
            println("$it")
        }
    }


    delay(8000)
}


