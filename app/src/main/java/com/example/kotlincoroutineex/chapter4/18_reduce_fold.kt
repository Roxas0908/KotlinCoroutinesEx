package com.example.kotlincoroutineex.chapter4


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.fold
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
        flow1.fold("ha") { acc, i -> acc + i }.let {
            println("flow1.fold(10): $it")
        }

        flow1.runningFold("ha") { acc, i -> acc + i}.collect {
            println("flow1.runningFold(\"ha\"): $it")
        }

        flow1.scan("ha") { acc, i -> acc + i}.collect {
            println("flow1.runningFold(\"ha\"): $it")
        }

//        val sum = flow1.runningReduce { acc, i -> acc + i }.collect {
//            println("flow1.runningReduce(): $it")
//        }
    }

//    scope.launch {
//        val sum = flow1.reduce { acc, i -> acc + i }
//
//        println("flow1.reduce(): $sum")
//    }

    val list = listOf(1, 2, 3, 4)

//    list.runningFold("ha") { acc, i -> acc + i }.let {
//        print("list.runningFold(\"ha\"): $it")
//    }
    
//    list.fold("ha") { acc, i -> acc + i }.let {
//        println("list.fold(10): $it")
//    }

//    list.runningReduce { acc, i -> acc + i }.let {
//        println("list.runningReduce(): $it")
//    }

//    list.reduce { acc, i -> acc + i }.let {
//        println("list.reduce(): $it")
//    }


    delay(8000)
}


