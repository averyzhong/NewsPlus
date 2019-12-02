package com.avery.newsplus.list.datasource

import androidx.paging.PageKeyedDataSource
import com.avery.newsplus.api.model.NewsMetaItem
import com.avery.newsplus.list.repository.NewsListRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * 新闻列表数据源
 *
 * @author Avery
 */

class NewsMetaListDataSource(
    private val categoryId: Int,
    private val coroutineScope: CoroutineScope,
    private val repository: NewsListRepository
) : PageKeyedDataSource<Int, NewsMetaItem>() {

    var exceptionHandler = CoroutineExceptionHandler {  coroutineContext, throwable ->
        print("Throws an exception with message: ${throwable.message}")
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, NewsMetaItem>) {
        coroutineScope.launch(exceptionHandler) {
            val response = repository.getNewsList(categoryId,1, params.requestedLoadSize)
            val newsMetaItems = response.body()?.data?.list
            newsMetaItems?.let {
                callback.onResult(newsMetaItems, null, 2)
            }

        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, NewsMetaItem>) {
        coroutineScope.launch(exceptionHandler) {
            val response = repository.getNewsList(categoryId, params.key, params.requestedLoadSize)
            val newsMetaItems = response.body()?.data?.list
            newsMetaItems?.let {
                callback.onResult(newsMetaItems, params.key + 1)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, NewsMetaItem>) {

    }

}