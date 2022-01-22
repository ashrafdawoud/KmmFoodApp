package com.example.ashtaftestkmm.Food2ForkKmm.Domain.Utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach



fun <T>Flow<T>.asCommonFlow():CommonFlow<T> = CommonFlow(this)

class CommonFlow<T>(private val orign:Flow<T>):Flow<T> by orign{
    fun collectFlow(
        coroutineScope:CoroutineScope?=null ,
        callBack:(T)->Unit
    ){
        onEach {
            callBack(it)
        }.launchIn(coroutineScope ?: CoroutineScope(Dispatchers.Main))
    }
}