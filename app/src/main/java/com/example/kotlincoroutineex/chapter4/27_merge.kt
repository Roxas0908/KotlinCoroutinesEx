package com.example.kotlincoroutineex.chapter4


import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flattenConcat
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.NullPointerException
import kotlin.coroutines.EmptyCoroutineContext



/**
 * author: liumingsong
 * created on: 2025/7/2 16:45
 * description:
 */
fun main(): Unit = runBlocking {
    val scope = CoroutineScope(EmptyCoroutineContext)
    val flow1 = flow {
        delay(500)
        emit(1)
        delay(500)
        emit(2)
        delay(500)
        emit(3)
    }
    val flow2 = flow {
        delay(250)
        emit(4)
        delay(500)
        emit(5)
        delay(500)
        emit(6)
    }

    // combine()
    val combinedFlow = flow1.combine(flow2) { a, b ->
        "$a & $b"
    }
    val combinedFlow2 = combine(flow1, flow2) { a, b ->
        "$a & $b"
    }
    val zippedFlow = flow1.zip(flow2) { a, b ->
        "$a & $b"
    }
    val combineTransform = flow1.combineTransform(flow2) { a, b ->
        emit("$a & $b")
    }

    combineTransform.collect {
        println(it)
    }


    //
    val flow3 = flow1.map { from -> (1 .. from).asFlow().map { "$from: $it" } }
    val concatenatedMappedFlow = flow1.flatMapConcat { from -> (1 .. from).asFlow().map { "$from: $it" } }
//    val concatenatedMergedFlow = flow1.flatMapMerge { from ->
//        (1..from).asFlow().map {
//            delay(500)
//            "$from: $it"
//        }
//    }
//
//    concatenatedMergedFlow.collect {
//        println(it)
//    }

    // flatten
    val flowFlow = flowOf(flow1, flow2)
    val concatedFlowFlow = flowFlow.flattenConcat()
    val mergedFlowFlow = flowFlow.flattenMerge()

//    scope.launch {
//        mergedFlowFlow.collect {
//            println("$it")
//        }
//    }

//    scope.launch {
//        concatedFlowFlow.collect {
//            println("$it")
//        }
//    }

    // merge
    val merge = merge(flow1, flow2)
    val mergedFromList = listOf(flow1, flow2).merge()

//    scope.launch {
//        mergedFromList.collect {
//            println("$it")
//        }
//    }



    delay(8000)
}


