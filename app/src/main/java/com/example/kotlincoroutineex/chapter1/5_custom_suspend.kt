package com.example.kotlincoroutineex.chapter1

import com.example.kotlincoroutineex.common.Contributor
import com.example.kotlincoroutineex.common.gitHub


/**
 * author: liumingsong
 * created on: 2025/6/18 11:34
 * description:
 */

suspend fun getRetrofitContributors(): List<Contributor> {
    return gitHub.contributors("square", "retrofit")
}

suspend fun customSuspendFun() {

}

