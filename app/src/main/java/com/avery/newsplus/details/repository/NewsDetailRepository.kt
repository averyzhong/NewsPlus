package com.avery.newsplus.details.repository

import com.avery.newsplus.api.NewsService

/**
 * 新闻详情数据仓库
 *
 * @author Avery
 */

class NewsDetailRepository(private val api: NewsService) {

    suspend fun getNewsDetails(newsId: String) = api.getNewsDetails(newsId)
}