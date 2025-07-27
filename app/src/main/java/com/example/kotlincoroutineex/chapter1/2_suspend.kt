package com.example.kotlincoroutineex.chapter1

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.example.kotlincoroutineex.R
import com.example.kotlincoroutineex.common.Contributor
import com.example.kotlincoroutineex.common.gitHub
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * author: liumingsong
 * created on: 2025/6/14 22:08
 * description:
 */
class SuspendActivity: ComponentActivity() {

    private lateinit var infoTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        infoTextView = findViewById(R.id.infoTextView)



    }

    private fun coroutinesStyle() = CoroutineScope(Dispatchers.Main).launch {
        val contributors = gitHub.contributors("square", "retrofit")

        showContributors(contributors)
    }

    private fun showContributors(contributors: List<Contributor>) = contributors
        .map { "${it.login} (${it.contributions})" }
        .reduce {acc, s -> "$acc\n$s"}
        .let {infoTextView.text = it}

}
