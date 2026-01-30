package com.example.kotlincoroutineex.chapter4

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.EmptyCoroutineContext

/**
 * author: liumingsong
 * created on: 2026/1/29 14:11
 * description:
 */
fun main(): Unit = runBlocking {
    val scope = CoroutineScope(EmptyCoroutineContext)
    val flow1 = flowOf(1, 2, 3, 4, 5)

    scope.launch {
        val list = mutableListOf<Int>()

        flow1.toList(list)
        println("$list")
    }

//    scope.launch {
//        println("flow1.count { it > 3 }: ${flow1.count { it > 3 }}")
//    }


//    scope.launch {
//        println("flow1.first(): ${flow1.first()}")
//        println("flow1.first() with condition: ${flow1.first { it > 3}}")
//    }



    delay(8000)
}

