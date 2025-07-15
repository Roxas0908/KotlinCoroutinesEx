package com.example.kotlincoroutineex.chapter1

import android.os.Bundle
import androidx.activity.ComponentActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * author: liumingsong
 * created on: 2025/6/14 22:08
 * description:
 */
class SuspendActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

//    private fun coroutinesStyle() = CoroutineScope(Dispatchers.Main).launch {
//        val contributors = gitHub.contributors()
//
//        showContributors(contributors)
//    }

//    private fun showContributors(contributors: List<Contributors>) = contributors
//        .map { "${it.login} (${it.contributions})" }
//        .reduce {acc, s -> "$acc\n$s"}
//        .let {binding.infoTextView.text = it}

}
