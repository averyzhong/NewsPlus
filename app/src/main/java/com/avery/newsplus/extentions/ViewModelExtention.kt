package com.avery.newsplus.extentions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * ViewModel扩展，添加协程构建器工具方法
 *
 * @author Avery
 */

var exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    print("Throws an exception with message: ${throwable.message}")
}

fun ViewModel.launch(
    context: CoroutineContext = viewModelScope.coroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job = viewModelScope.launch(context + exceptionHandler, start, block)