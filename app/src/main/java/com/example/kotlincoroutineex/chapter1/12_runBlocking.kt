package com.example.kotlincoroutineex.chapter1

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.kotlincoroutineex.common.gitHub
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */


fun main() = runBlocking {

    val contributor = gitHub.contributors("square", "retrofit")

    launch {

    }
}

suspend fun main2() = coroutineScope {

    val contributor = gitHub.contributors("square", "retrofit")

    launch {

    }
}


class RunBlockingActivity : ComponentActivity() {

    private lateinit var infoTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_1)
        infoTextView = findViewById(R.id.infoTextView)

        val scope = CoroutineScope(Dispatchers.Main).launch {

        }
        scope.cancel()


        runBlocking {
            gitHub.contributors("square", "retrofit")
        }

    }

    private fun blockingContributors() = runBlocking {
        gitHub.contributors("square", "retrofit")
    }

}