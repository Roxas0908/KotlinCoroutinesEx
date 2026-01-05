package com.example.kotlincoroutineex.chapter4


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.scan
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

    scope.launch {
        val flowOnEach = flow1.onEach {
            println("onEach(): $it")
        }.onEach {
            println("onEach(): $it")
        }.filter {
            it % 2 == 0
        }.onEach {
            println("onEach(): $it")
        }

        flowOnEach.collect {
            println("collect(): $it")
        }
    }


    delay(8000)
}


