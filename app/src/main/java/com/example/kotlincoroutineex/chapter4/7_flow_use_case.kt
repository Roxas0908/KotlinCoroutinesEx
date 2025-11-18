package com.example.kotlincoroutineex.chapter4

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.coroutines.EmptyCoroutineContext


/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
fun main(): Unit = runBlocking {
    val scope = CoroutineScope(EmptyCoroutineContext)
    val numsFlow = flow {
        emit(1)
        delay(2000)
        emit(2)
        emit(3)
    }

    scope.launch {
        numsFlow.collect {
            println("A: $it")
        }
    }

    scope.launch {
        delay(5000)
        numsFlow.collect {
            println("B: $it")
        }
    }

    scope.launch {
        while (true) {
            println("Weather: ${getWeather()}")
            delay(1000)
        }
    }

    scope.launch {
//        showWeather(weatherFlow)
        weatherFlow.collect {
            println("Weather:${it}")
        }
    }

    scope.launch {
        while (true) {
            showWeather(getWeather())
        }
    }

    delay(8000)
}


val weatherFlow = flow {
    while (true) {
        emit(getWeather())
    }
}

suspend fun showWeather(flow: Flow<String>) {
    flow.collect {
        println("Weather:${it}")
    }
}

suspend fun showWeather(weather: String) {
    println("Weather:$weather")
}

suspend fun getWeather() = withContext(Dispatchers.IO) {
    "Sunny"

}






