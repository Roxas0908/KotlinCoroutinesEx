package com.example.kotlincoroutineex.chapter4


import com.example.kotlincoroutineex.common.gitHub
import com.example.kotlincoroutineex.common.unstableGitHub
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.chunked
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeoutException
import kotlin.coroutines.EmptyCoroutineContext


/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
fun main(): Unit = runBlocking {
    val scope = CoroutineScope(EmptyCoroutineContext)
    val flow1 = flowOf(1, 2, 3, 4, 5)
    val flow2 = flow {
        for (i in 1..5) {
            if (i == 3) {
                throw CancellationException()
            } else {
                emit(i)
            }
        }
    }.catch {
        println("catch(): $it")
        emit(100)
        emit(200)
    }
//        .onEach {
//        throw RuntimeException("Exception from onEach()")
//    }.catch {
//        println("catch(): $it")
//    }

    scope.launch {
        try {
            flow2.collect {
//                val contributors = unstableGitHub.contributors("square", "retrofit")
//
//                println("contributors: $contributors")
                println("collect(): $it")
            }
        } catch (e: Exception) {
            println("Network error: $e")
        }
    }


    delay(8000)
}
