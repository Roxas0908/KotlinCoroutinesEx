package com.example.kotlincoroutineex.chapter4


import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.NullPointerException
import kotlin.coroutines.EmptyCoroutineContext



/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
fun main(): Unit = runBlocking {
    val scope = CoroutineScope(EmptyCoroutineContext)
    val flow1 = flow {
        emit(1)
        delay(500)
        emit(2)
        delay(500)
        emit(3)
    }

    val sharedFlow = flow1.shareIn(scope, SharingStarted.WhileSubscribed(), 2)

    scope.launch {
        val parent = this

        launch {
            delay(4000)
            parent.cancel()
        }

        delay(1500)
        sharedFlow.collect {
            println("collect()-1: $it")
        }
    }
    scope.launch {
        delay(5000)
        sharedFlow.collect {
            println("collect()-2: $it")
        }
    }


    delay(8000)
}


