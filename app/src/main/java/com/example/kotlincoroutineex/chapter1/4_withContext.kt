package com.example.kotlincoroutineex.chapter1

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * author: liumingsong
 * created on: 2025/6/14 22:08
 * description:
 */
class WithContextActivity: ComponentActivity() {

    private lateinit var infoTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            val data = withContext(Dispatchers.IO) {
                // 网络代码
                "data"
            }
            val processData = withContext(Dispatchers.Default) {
                // 处理数据
                "process $data"
            }
            println("Processed data: $processData")
        }
    }
    


    class MyViewModel: ViewModel() {
        fun someFun() {
            viewModelScope.launch {

            }
        }
    }

}
