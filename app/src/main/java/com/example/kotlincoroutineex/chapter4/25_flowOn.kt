package com.example.kotlincoroutineex.chapter4


import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
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
        println("CoroutineContext in flow { }: ${currentCoroutineContext()}")
        for (i in 1..5) {
            emit(i)
        }
    }.flowOn(Dispatchers.IO).flowOn(Dispatchers.Default)
    .map {
        println("CoroutineContext in map(): ${currentCoroutineContext()}")
        it
    }
    .flowOn(newFixedThreadPoolContext(2, "TestPool"))

    val flow3 = channelFlow {
        println("CoroutineContext in flow { }: ${currentCoroutineContext()}")
        for (i in 1..5) {
            send(i)
        }
    }.flowOn(Dispatchers.IO)

    scope.launch {
        flow3.collect {
            if (it == 1) {
                println("CoroutineContext in collect(): ${currentCoroutineContext()}")
            }
            println("$it")
        }

//        flow2.collect {
//            if (it == 1) {
//                println("CoroutineContext in collect(): ${currentCoroutineContext()}")
//            }
//            println("$it")
//        }
    }


    delay(8000)
}


