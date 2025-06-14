package com.example.kotlincoroutineex

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kotlincoroutineex.ui.theme.KotlinCoroutineExTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.newSingleThreadContext
import java.util.concurrent.Executors
import kotlin.concurrent.thread
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Thread {

        }.start()

        thread {

        }

        println("Main thread: ${Thread.currentThread().name}")

        val executor = Executors.newCachedThreadPool()
        executor.execute {
            println("Executor thread: ${Thread.currentThread().name}")
        }

        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch(Dispatchers.Default) {
            println("CoroutineScope thread: ${Thread.currentThread().name}")
        }

        val threadPoolContext = newFixedThreadPoolContext(10, "threadPool1")
        scope.launch(threadPoolContext) {

        }

        val singleThreadContext = newSingleThreadContext("singleThread1")



        val handler = Handler(Looper.getMainLooper())
        handler.post {

        }

        val view = View(this)
        view.post {

        }


        GlobalScope.launch {

        }


    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinCoroutineExTheme {
        Greeting("Android")
    }
}