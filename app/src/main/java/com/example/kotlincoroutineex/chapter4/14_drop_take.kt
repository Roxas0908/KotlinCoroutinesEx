package com.example.kotlincoroutineex.chapter4


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.takeWhile
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
        flow1.drop(2).collect { println("flow1.drop(): $it") }
        flow1.dropWhile { it < 3 }.collect { println("flow1.dropWhile(): $it") }
        flow1.take(2).collect { println("flow1.take(): $it") }
        flow1.takeWhile { it > 3 }.collect { println("flow1.takeWhile(): $it") }
    }




    delay(8000)
}


