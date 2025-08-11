package com.example.kotlincoroutineex.chapter2

import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread


/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
fun main(): Unit = runBlocking {
    val thread = thread {
        println("thread running")
    }

    thread.stop()

}



