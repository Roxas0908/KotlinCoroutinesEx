package com.example.kotlincoroutineex.chapter4

import android.util.Log
import androidx.compose.animation.scaleIn
import com.example.kotlincoroutineex.common.Contributor
import com.example.kotlincoroutineex.common.gitHub
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.FileWriter
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.coroutineContext


/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
fun main(): Unit = runBlocking {
    val scope = CoroutineScope(EmptyCoroutineContext)

    val nums = sequence {
        var n = 1

        while (true) {
            yield(n++)
//            yield(getData())
        }
//        yield(1)
//        yield(2)
    }

    val numsFlow = flow {
        var n = 1

        withContext(Dispatchers.IO) {  }
        while (true) {
            emit(getData())
        }
    }.map { "number $it" }

    scope.launch {
        numsFlow.collect {
            println(it)
        }
    }


//    val list = buildList {
//        var n = 1
//
//        add(1)
//        add(2)
//    }

//    scope.launch {
//        for (num in list) {
//            println(num)
//            delay(1000)
//        }
//    }

    scope.launch {
        for (num in nums) {
            println(num)
            delay(1000)
        }
    }


    delay(10000)
}


suspend fun getData(): Int {
    return 1
}




