package com.example.kotlincoroutineex.chapter3

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.EmptyCoroutineContext


/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
fun main() = runBlocking {
    CoroutineScope(EmptyCoroutineContext).launch {
        
    }

    val job = GlobalScope.launch {
        coroutineContext[Job]

    }
    println("job parent: ${job.parent}")

    delay(10000)
}



