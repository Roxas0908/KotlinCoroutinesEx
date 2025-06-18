package com.example.kotlincoroutineex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * author: liumingsong
 * created on: 2025/6/14 22:08
 * description:
 */
class AndroidActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {

        }
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


    class MyViewModel: ViewModel() {
        fun someFun() {
            viewModelScope.launch {

            }
        }
    }

}
