package com.example.kotlincoroutineex.chapter1

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.example.kotlincoroutineex.common.Contributor
import com.example.kotlincoroutineex.common.gitHub
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
class CallbackToSuspendActivity : ComponentActivity() {

    private lateinit var infoTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_1)
        infoTextView = findViewById(R.id.infoTextView)

        //        val job = lifecycleScope.launch {
//            println("Coroutine cancel: 1")
//            Thread.sleep(500)
//            println("Coroutine cancel: 2")
//        }
//        job.cancel()


        val job = lifecycleScope.launch {
            try {
                val contributors = callbackToCancellableSuspend()
                showContributors(contributors!!)
            } catch (e: Exception) {
                infoTextView.text = e.message
            }
        }
        job.cancel()
    }

    private suspend fun callbackToCancellableSuspend() = suspendCancellableCoroutine {
        gitHub.contributorsCall("square", "retrofit")
            .enqueue(object : Callback<List<Contributor>> {
                override fun onResponse(call: Call<List<Contributor>>, response: Response<List<Contributor>>, ) {
                    it.resume(response.body())
                }

                override fun onFailure(call: Call<List<Contributor>>, t: Throwable) {
                    it.resumeWithException(t)
                }
            })
    }

    private suspend fun callbackToSuspend() = suspendCoroutine {
        gitHub.contributorsCall("square", "retrofit")
            .enqueue(object : Callback<List<Contributor>> {
                override fun onResponse(call: Call<List<Contributor>>, response: Response<List<Contributor>>, ) {
                    it.resume(response.body())
                }

                override fun onFailure(call: Call<List<Contributor>>, t: Throwable) {
                    it.resumeWithException(t)
                }
            })
    }

    private fun callbackStyle() {
        gitHub.contributorsCall("square", "retrofit")
            .enqueue(object : Callback<List<Contributor>> {
                override fun onResponse(
                    call: Call<List<Contributor>>, response: Response<List<Contributor>>,
                ) {
                    showContributors(response.body()!!)
                }

                override fun onFailure(call: Call<List<Contributor>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
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