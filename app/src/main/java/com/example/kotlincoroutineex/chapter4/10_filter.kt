package com.example.kotlincoroutineex.chapter4


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
    val flow1 = flowOf(1, 2, null, 3, 4, null, 5, listOf("123"), listOf(1, 2, 3))

    scope.launch {
//        flow1.filterNotNull().filter {it % 2 == 0 }.collect {
//            println("flow1.filter: $it")
//        }
//        flow1.filterNotNull().filterNot {it % 2 == 0 }.collect {
//            println("flow1.filterNot: $it")
//        }
        flow1.filter { it is List<*> && it.firstOrNull()?.let { item -> item is String } == true }.collect {
            println("flow1: $it")
        }

    }




    delay(8000)
}








