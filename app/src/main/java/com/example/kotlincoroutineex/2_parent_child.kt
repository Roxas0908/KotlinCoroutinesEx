package com.example.kotlincoroutineex

import androidx.compose.animation.scaleIn
import com.example.kotlincoroutineex.common.gitHub
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
fun main(): Unit = runBlocking {

    val scope = CoroutineScope(Dispatchers.IO)
    var innerJob: Job? = null
    val job = scope.launch {
        val customJob = Job()
        launch(Job()) {
            delay(500)
        }
    }
    val startTime = System.currentTimeMillis()
    job.join()
    println("duration: ${System.currentTimeMillis() - startTime}")

//    println("children count: ${job.children.count()}")
//    println("job.children.elementAt(0) === innerJob: ${job.children.elementAt(0) === innerJob}")





}



