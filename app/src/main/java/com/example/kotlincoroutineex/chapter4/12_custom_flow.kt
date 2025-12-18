package com.example.kotlincoroutineex.chapter4


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
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
    val flow1 = flowOf(1, 2, 3)
    val flow2 = flowOf("roxas", "Roxas", "ROXAS")

    scope.launch {
        flow1.customOperator().collect {

        }

//        flow1.customOperator().collect(object : FlowCollector<Int> {
//            override suspend fun emit(value: Int) {
//                println(value)
//            }
//        })

        flow1.multiply2().collect(object : FlowCollector<Int> {
            override suspend fun emit(value: Int) {
                println(value)
            }
        })
    }

    delay(8000)
}

fun <T> Flow<T>.customOperator(): Flow<T> = flow {
    this@customOperator.collect {
        emit(it)
        emit(it)
    }
}

fun Flow<Int>.multiply2(): Flow<Int> = channelFlow {
    this@multiply2.collect {
        send(it * 2)
    }
}



