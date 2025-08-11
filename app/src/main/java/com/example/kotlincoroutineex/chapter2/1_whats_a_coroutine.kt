package com.example.kotlincoroutineex.chapter2

import com.example.kotlincoroutineex.common.gitHub
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
fun main(): Unit = runBlocking {

    val contributor = gitHub.contributors("square", "retrofit")

    val scope = CoroutineScope(Dispatchers.IO)
    var innerJob: Job? = null
    var innerScope: CoroutineScope? = null
    val outerJob = scope.launch(Dispatchers.Default) {
        innerJob = coroutineContext[Job]
        innerScope = this
    }
    println("outerJob: $outerJob")
    println("innerJob: $innerJob")
    println("outerJob === innerScope: ${outerJob === innerScope}")





//    launch {
//
//    }
//
//    Thread {
//
//    }.start()
//
//    val thread = thread {
//
//    }
//    thread.name
//    thread.interrupt()




}



