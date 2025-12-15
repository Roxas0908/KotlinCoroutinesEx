package com.example.kotlincoroutineex.chapter4


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.filterNot
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.EmptyCoroutineContext


/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
fun main(): Unit = runBlocking {
    val scope = CoroutineScope(EmptyCoroutineContext)
    val flow1 = flowOf(1, 2, 2, 3, 3, 3, 1)
    val flow2 = flowOf("roxas", "Roxas", "ROXAS")

    scope.launch {
//        flow1.distinctUntilChanged().collect {
//            println("flow1.distinctUntilChanged(): $it")
//        }

//        flow2.distinctUntilChanged().collect {
//            println("flow2.distinctUntilChanged(): $it")
//        }
//        flow2.distinctUntilChanged { old, new -> old.uppercase() == new }.collect {
//            println("flow2.distinctUntilChanged { old, new -> old.uppercase() == new }: $it")
//        }

        flow2.distinctUntilChangedBy { it.uppercase() }.collect {
            println("flow2.distinctUntilChangedBy { it.uppercase() }: $it")
        }
    }




    delay(8000)
}








