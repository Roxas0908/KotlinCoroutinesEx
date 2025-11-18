package com.example.kotlincoroutineex.chapter4

import com.example.kotlincoroutineex.common.Contributor
import com.example.kotlincoroutineex.common.gitHub
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.cancellation.CancellationException


/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
fun main(): Unit = runBlocking {
    val scope = CoroutineScope(EmptyCoroutineContext)

    val flow1 = flowOf(1, 2, 3)
    val flow2 = listOf(1, 2, 3).asFlow()
    val flow3 = setOf(1, 2, 3).asFlow()
    val flow4 = sequenceOf(1, 2, 3).asFlow()

    val channel = Channel<Int>()
    val flow5 = channel.consumeAsFlow()
    val flow6 = channel.receiveAsFlow()
    val flow7 = channelFlow {
        launch {
            delay(1000)
            send(2)
        }
        delay(1000)
        send(1)
    }

    scope.launch {
        flow5.collect {
            println("Flow6: $it")
        }
    }
    scope.launch {
//        flow5.collect {
//            println("Flow6: $it")
//        }
    }

    channelFlow {
        delay(1000)
        send(1)
    }
    flow {
        delay(1000)
        emit(1)
    }

    scope.launch {
        flow7.collect {
            println("channelFlow: $it")
        }
    }

    val flow9 = channelFlow {
        gitHub.contributorsCall("square", "retrofit")
            .enqueue(object : Callback<List<Contributor>> {
                override fun onResponse(p0: Call<List<Contributor>>, p1: Response<List<Contributor>>) {
                    trySend(p1.body())
                    close()
                }

                override fun onFailure(p0: Call<List<Contributor>>, p1: Throwable) {
                    cancel(CancellationException(p1))
                }
            })
        awaitClose()
    }

    scope.launch {
        flow9.collect {
            println("channelFlow with callback: $it")
        }
    }

    channel.send(1)
    channel.send(2)
    channel.send(3)
    channel.send(4)


    delay(8000)
}








