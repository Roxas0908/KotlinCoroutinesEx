package com.example.kotlincoroutineex

import android.os.Bundle
import androidx.activity.ComponentActivity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Timer
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class DraftActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        main()


    }

    suspend fun suspendMyDelay(ms: Long, dispatcher: CoroutineDispatcher) =
        suspendCoroutine<Unit> { continuation ->
            // 使用 delay 模拟异步等待
            Timer().schedule(object : java.util.TimerTask() {
                override fun run() {
                    // 使用指定 dispatcher 恢复协程
                    dispatcher.dispatch(continuation.context) {
                        continuation.resume(Unit)
                    }
                }
            }, ms)
        }

    fun main() = runBlocking {
        println("main: ${Thread.currentThread().name}")

        launch(Dispatchers.Unconfined) {
            println("Unconfined - before: ${Thread.currentThread().name}")
            suspendMyDelay(800, Dispatchers.Default)
            println("Unconfined - after: ${Thread.currentThread().name}")
        }

        launch(Dispatchers.Default) {
            println("Default - before: ${Thread.currentThread().name}")
            suspendMyDelay(800, Dispatchers.Default)
            println("Default - after: ${Thread.currentThread().name}")
        }

    }

    
}
