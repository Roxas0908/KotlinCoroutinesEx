package com.example.kotlincoroutineex.chapter2

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
fun main() = runBlocking {

    val job = launch(Dispatchers.Default) {
        var count = 0

        while (true) {
            try {
                delay(500)
            } catch (e: CancellationException) {
                println("Cancelled")
                throw e
            }

            count++
            println("count: $count")
        }
    }

    delay(1000)
    job.cancel()
}



