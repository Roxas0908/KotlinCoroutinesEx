package com.example.kotlincoroutineex.chapter4


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
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
    var flow: Flow<Int>? = null

    scope.launch(Dispatchers.IO) {
        flow = flow {
            delay(2000)
            emit(2)
        }
    }

    delay(2000)

    scope.launch(Dispatchers.IO) {
        flow?.collect {
            delay(1000)
            println("flow: $it")
        }
    }

    flow?.onEach {
        println("flow: $it")
    }?.launchIn(scope)

    scope.launch {
        flow?.collect {
            println("flow: $it")
        }
    }

    flow?.collectIndexed { index, value ->  }

    flow?.collectLatest {

    }

    delay(8000)
}








