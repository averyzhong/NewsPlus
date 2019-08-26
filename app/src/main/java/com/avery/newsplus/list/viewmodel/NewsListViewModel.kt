package com.avery.newsplus.list.viewmodel

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.avery.newsplus.api.model.NewsMetaItem
import com.avery.newsplus.list.datasource.NewsMetaListDataSourceFactory
import com.avery.newsplus.list.repository.NewsListRepository

class NewsListViewModel(
    private val repository: NewsListRepository
) : ViewModel() {

    private val categoryIdLiveData = MutableLiveData<Int>()

    val newsMetaItems: LiveData<PagedList<NewsMetaItem>> = Transformations.switchMap(categoryIdLiveData) { categoryId ->
        val dataSourceFactory = NewsMetaListDataSourceFactory(viewModelScope, categoryId, repository)
        return@switchMap LivePagedListBuilder(
            dataSourceFactory,
            PagedList.Config.Builder()
                .setPageSize(20)
                .setInitialLoadSizeHint(20)
                .build()
        ).build()
    }

    fun loadNewsList(categoryId: Int) {
        categoryIdLiveData.value = categoryId
    }

}