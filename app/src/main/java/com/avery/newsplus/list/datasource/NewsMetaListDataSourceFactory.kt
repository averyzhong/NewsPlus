package com.avery.newsplus.list.datasource

import androidx.paging.DataSource
import com.avery.newsplus.api.model.NewsMetaItem
import com.avery.newsplus.list.repository.NewsListRepository
import kotlinx.coroutines.CoroutineScope

class NewsMetaListDataSourceFactory(
    private val coroutineScope: CoroutineScope,
    private val categoryId: Int,
    private val repository: NewsListRepository
) : DataSource.Factory<Int, NewsMetaItem>() {
    override fun create(): DataSource<Int, NewsMetaItem> = NewsMetaListDataSource(
        categoryId,
        coroutineScope,
        repository
    )
}