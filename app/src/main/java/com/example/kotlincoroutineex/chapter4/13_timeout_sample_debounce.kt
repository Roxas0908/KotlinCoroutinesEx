package com.example.kotlincoroutineex.chapter4


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.flow.timeout
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds


/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
fun main(): Unit = runBlocking {
    val scope = CoroutineScope(EmptyCoroutineContext)
    val flow1 = flowOf(1, 2, 3)
    val flow2 = flow {
        delay(500)
        emit(0)
        emit(1)
        delay(900)
        emit(1.5)
        emit(2)
        delay(2000)
        emit(3)
    }

    scope.launch {
        flow2.sample(1.seconds).collect {
            println("flow2: $it")
        }

//        flow2.timeout(1.seconds).collect {
//            println("flow2: $it")
//        }

        try {
            flow1.timeout(5.seconds).collect {
                // 显示正在输入
            }
        } catch (e: TimeoutCancellationException) {
            // 关闭提示
        }

    }

    delay(8000)
}

fun <T> Flow<T>.throttle(duration: Duration): Flow<T> = flow {
    var lastTime = 0L

    this@throttle.collect {
        if (System.currentTimeMillis() - lastTime >= duration.inWholeMilliseconds) {
            emit(it)
            lastTime = System.currentTimeMillis()
        }
    }
}

