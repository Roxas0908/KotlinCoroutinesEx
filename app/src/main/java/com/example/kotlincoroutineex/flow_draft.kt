package com.example.kotlincoroutineex


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.chunked
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
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
        emit(generate())
    }

    scope.launch {
        flow2.collect {
            println("flow2: $it")
        }
    }

    println("${Thread.currentThread()}")

//    scope.launch(Dispatchers.Default) {
//        flow1.collect {
//            println("collect() Thread: ${Thread.currentThread()}")
//            println("$it")
//        }
//    }

//    slowNumbers().collect {
//        println("$it")
//        delay(500)
//    }


    delay(8000)
}

fun slowNumbers(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(1000) // 慢生产：每秒生成一个值
        emit(i)
    }
}.flowOn(Dispatchers.IO) // 生产者在 IO 线程并发生成

suspend fun generate(): Int = withContext(Dispatchers.IO) {
    0
}