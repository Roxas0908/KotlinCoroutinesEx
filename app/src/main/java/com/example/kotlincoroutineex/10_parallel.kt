package com.example.kotlincoroutineex

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.example.kotlincoroutineex.common.Contributor
import com.example.kotlincoroutineex.common.gitHub
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors
import kotlin.concurrent.thread

/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
class ParallelActivity : ComponentActivity() {

    private lateinit var infoTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_1)
        infoTextView = findViewById(R.id.infoTextView)


        lifecycleScope.launch {
            val contributors = coroutineScope {
                val deferred1 = async {
                    gitHub.contributors("square", "retrofit")
                }
                val deferred2 = async {
                    gitHub.contributors("square", "okhttp")
                }
                deferred1.await() + deferred2.await()
            }

            showContributors(contributors)
        }

        lifecycleScope.launch {
            val initJob = launch {
//                init()
            }
            val contributors = gitHub.contributors("square", "retrofit")

            initJob.join()
//            processData()
        }

    }

    private fun coroutinesStyle() = lifecycleScope.launch {
        val contributors1 = gitHub.contributors("square", "retrofit")
        val contributors2 = gitHub.contributors("square", "okhttp")
        showContributors(contributors1 + contributors2)
    }

    private suspend fun showContributorsOfRetrofit(): List<Contributor> {
        return gitHub.contributors("square", "retrofit")
    }

    private fun showContributors(contributors: List<Contributor>) = contributors
        .map { "${it.login} (${it.contributions})" }
        .reduce { acc, s -> "$acc\n$s" }
        .let { infoTextView.text = it }

}