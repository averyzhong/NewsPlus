package com.avery.newsplus.extentions

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avery.newsplus.App
import com.avery.newsplus.api.NewsService
import com.avery.newsplus.api.ServiceFactory
import com.avery.newsplus.db.AppDataBase
import kotlinx.coroutines.*
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * 一些扩展
 *
 * @author Avery
 */

/** ViewMode扩展，添加协程构建器工具方法 */
fun ViewModel.launch(
    context: CoroutineContext = viewModelScope.coroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job = viewModelScope.launch(context + exceptionHandler, start, block)

/** View扩展获取Application方法 */
fun View.application() = App.instance()

/** Fragment扩展获取数据库方法 */
fun Fragment.database() = AppDataBase.instance()

/** Fragment扩展获取Api service方法 */
fun <T> Fragment.getApiService(apiServiceClass: Class<T>) = ServiceFactory.getService(apiServiceClass)

/** Fragment扩张打开Activity简单方法 */
fun <T : Activity> Fragment.startActivity(activityClass: Class<T>, args: Bundle? = null) {
    val intent = Intent(context, activityClass)
    args?.let {
        intent.putExtras(it)
    }
    startActivity(intent)
}

/** 协程异常处理器 */
var exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
    print("Throws an exception with message: ${throwable.message}")
}
