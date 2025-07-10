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
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread

/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
class WithContext3Activity : ComponentActivity() {
    private lateinit var infoTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_1)
        infoTextView = findViewById(R.id.infoTextView)

        CoroutineScope(Dispatchers.Default).launch {
            val data = getData()
            val processedData = processData(data)

            println("Processed data: $processedData")
        }

        val handler = Handler(Looper.getMainLooper())
        thread {
            handler.post {

            }
        }
    }

    private suspend fun getData() = withContext(Dispatchers.IO) {
        // 网络代码
        "data"
    }

    private suspend fun processData(data: String) = withContext(Dispatchers.Default) {
        // 处理数据
        "processed $data"
    }

    private fun coroutinesStyle() = lifecycleScope.launch {
        val contributors = gitHub.contributors("square", "retrofit")
        showContributors(contributors)
    }

    private suspend fun showContributorsOfRetrofit(): List<Contributor> {
        return gitHub.contributors("square", "retrofit")
    }

    private fun showContributors(contributors: List<Contributor>) = contributors
        .map { "${it.login} (${it.contributions})" }
        .reduce { acc, s -> "$acc\n$s" }
        .let { infoTextView.text = it }

}