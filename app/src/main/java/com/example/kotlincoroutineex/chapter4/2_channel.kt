package com.example.kotlincoroutineex.chapter4

import android.util.Log
import androidx.compose.animation.scaleIn
import com.example.kotlincoroutineex.common.gitHub
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
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

//    val deferred1 = scope.async {
//        gitHub.contributors("square", "retrofit")
//    }
//    val deferred2 = scope.async {
//        gitHub.contributors("square", "okhttp")
//    }
//
//    println("Contributors: 1. ${deferred1.await()}, 2. ${deferred2.await()}")
//
//    val deferred = scope.async {
//        gitHub.contributors("square", "retrofit")
//    }
//
//    scope.launch {
//        delay(5000)
//        println("Contributors: ${deferred.await()}")
//    }

//    delay(1000)

    val receiver = scope.produce {
        while (isActive) {
            val data = gitHub.contributors("square", "retrofit")
            send(data)
        }
    }

    scope.launch {
        delay(5000)

        while (isActive) {
            println("Contributors: ${receiver.receive()}")
        }
    }

    delay(10000)
}







