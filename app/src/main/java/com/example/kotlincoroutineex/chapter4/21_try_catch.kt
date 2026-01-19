package com.example.kotlincoroutineex.chapter4


import com.example.kotlincoroutineex.common.gitHub
import com.example.kotlincoroutineex.common.unstableGitHub
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
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
        try {
            for (i in 1 .. 5) {
                // 数据库读数据
                // 网络请求
                emit(i)   // collect { } 是在这个 emit() 里面运行的
            }
        } catch (e: Exception) {
            println("Error in flow(): $e")
            throw e
        }
    }.map {
        throw NullPointerException()
    }.onEach {
        throw NullPointerException()
    }.transform<Int, Int> {
        val data = it * 2

        emit(data)
    }

    scope.launch {
        try {
            flow2.collect {
                val contributors = unstableGitHub.contributors("square", "retrofit")

                println("contributors: $contributors")

            }
        } catch (e: Exception) {
            println("Network Error")
        }
    }


    delay(8000)
}
