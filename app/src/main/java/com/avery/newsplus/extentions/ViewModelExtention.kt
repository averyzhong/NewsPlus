package com.fonsview.mangotv.extentions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun ViewModel.launch(
    context: CoroutineContext = viewModelScope.coroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job = viewModelScope.launch(context, start, block)