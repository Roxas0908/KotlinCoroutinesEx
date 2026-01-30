package com.example.kotlincoroutineex

import com.example.kotlincoroutineex.chapter4.getData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * author: liumingsong
 * created on: 2026/1/30 15:57
 * description:
 */
fun main(): Unit = runBlocking {
    val scope = CoroutineScope(EmptyCoroutineContext)

    val nums = sequence {
        var n = 1

        while (true) {
            Thread.sleep(1000)
            yield(n++)
        }
    }


    scope.launch(Dispatchers.IO) {
        for (num in nums) {
//            runOnUiThread {
//                println("nums sequence: $num")
//            }
        }
    }


    delay(10000)
}