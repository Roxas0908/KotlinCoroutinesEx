package com.example.kotlincoroutineex.chapter4


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.mapNotNull
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
//        flow1.map { it + 1 }.collect {
//            println("flow1.map { it + 1 }: $it")
//        }
//        flow1.mapNotNull { it + 1 }.collect {
//            println("flow1.mapNotNull { it + 1 }: $it")
//        }

//        flow1.map { if (it == 3) null else it + 1 }.collect {
//            println("flow1.map { it + 1 }: $it")
//        }
//        flow1.mapNotNull { if (it == 3) null else it + 1 }.collect {
//            println("flow1.mapNotNull { it + 1 }: $it")
//        }

        flow1.mapLatest {
            delay(120)
            it + 1
        }.collect {
            println("flow1.mapLatest { it + 1 }: $it")
        }
    }




    delay(8000)
}


