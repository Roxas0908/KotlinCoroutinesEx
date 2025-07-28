package com.example.kotlincoroutineex

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

        try {
            Thread.sleep(200)
        } catch (e: InterruptedException) {
            println("isInterrupted(): ${Thread.currentThread().isInterrupted}")
            println("Cleaning...")
            return@thread
        }

        val lock = Object()
        try {
            lock.wait()
        } catch (_: InterruptedException) {
            
        }


        println("thread done")
    }

    Thread.sleep(100)
    thread.interrupt()
}



