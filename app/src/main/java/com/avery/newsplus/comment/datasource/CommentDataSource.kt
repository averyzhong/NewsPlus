package com.avery.newsplus.comment.datasource

import androidx.paging.PageKeyedDataSource
import com.avery.newsplus.api.model.Comment
import com.avery.newsplus.comment.repository.CommentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CommentDataSource(
    private val coroutineScope: CoroutineScope,
    private val newsId: String,
    private val repository: CommentRepository
) : PageKeyedDataSource<Int, Comment>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Comment>) {
        coroutineScope.launch {
            val response = repository.getNewsComments(newsId, 1, params.requestedLoadSize)
            val comments = response.body()?.data?.list
            comments?.let {
                callback.onResult(it, null, 2)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Comment>) {
        // 后面才知道这个API不支持分页
        /*coroutineScope.launch {
            val response = repository.getNewsComments(newsId, params.key, params.requestedLoadSize)
            val comments = response.body()?.data?.list
            comments?.let {
                callback.onResult(it, params.key + 1)
            }
        }*/
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Comment>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}