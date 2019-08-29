package com.avery.newsplus.comment.viewmodel

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.avery.newsplus.api.model.Comment
import com.avery.newsplus.comment.datasource.CommentDataSourceFactory
import com.avery.newsplus.comment.repository.CommentRepository

class CommentViewModel(
    private val repository: CommentRepository
) : ViewModel() {

    private val newsIdLiveData = MutableLiveData<String>()

    val newsComments: LiveData<PagedList<Comment>> = Transformations.switchMap(newsIdLiveData) { newsId ->
        val dataSourceFactory = CommentDataSourceFactory(viewModelScope, newsId, repository)
        return@switchMap LivePagedListBuilder(
            dataSourceFactory,
            PagedList.Config.Builder()
                .setPageSize(20)
                .setInitialLoadSizeHint(20)
                .build()
        ).build()
    }

    fun loadNewsComments(newsId: String) {
        newsIdLiveData.value = newsId
    }
}