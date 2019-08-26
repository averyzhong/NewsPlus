package com.avery.newsplus.details.repository

import com.avery.newsplus.api.NewsService

class NewsDetailRepository(private val api: NewsService) {

    suspend fun getNewsDetails(newsId: String) = api.getNewsDetails(newsId)
}