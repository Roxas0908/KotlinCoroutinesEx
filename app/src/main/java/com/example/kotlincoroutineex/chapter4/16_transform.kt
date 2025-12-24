package com.example.kotlincoroutineex.chapter4


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.flow.transformLatest
import kotlinx.coroutines.flow.transformWhile
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
    val flow1 = flowOf(1, 2, 3, 4, 5)
    val flow2 = flow {
        delay(10)
        emit(1)
        delay(10)
        emit(2)
        delay(10)
        emit(3)
    }

    scope.launch {

        flow2.transformLatest {
            delay(20)
            if (it > 0) {
                repeat(it) { _ ->
                    emit("$it: hahaha")
                }
            }
        }.collect {
            println("flow2.transformLatest()-2: $it")
        }


//        flow1.transform {
//            if (it % 2 == 0) {
//                emit(it + 1)
//            }
//        }.collect {
//            println("flow1.transform(): $it")
//        }
//
//        flow1.transform {
//            if (it > 0) {
//                repeat(it) { _ ->
//                    emit("$it: hahaha")
//                }
//            }
//        }.collect {
//            println("flow1.transform()-2: $it")
//        }

//        flow1.transformWhile {
//            if (it > 0) {
//                repeat(it) { _ ->
//                    emit("$it: hahaha")
//                }
//            }
//            it <= 3
//        }.collect {
//            println("flow1.transform()-2: $it")
//        }

//        flow1.transformWhile {
//            if (it > 3) return@transformWhile false
//
//            if (it > 0) {
//                repeat(it) { _ ->
//                    emit("$it: hahaha")
//                }
//            }
//            true
//        }.collect {
//            println("flow1.transform()-2: $it")
//        }


//        flow1.map { it + 1 }
//        flow1.transform {
//            emit(it + 1)
//        }


    }




    delay(8000)
}


