package com.avery.newsplus.list.repository

import com.avery.newsplus.api.NewsService

/**
 * 新闻列表数据仓库
 *
 * @author Avery
 */

class NewsListRepository(private val service: NewsService) {

    suspend fun getNewsList(
        categoryId: Int,
        page: Int,
        pageSize: Int
    ) = service.getNewsList(categoryId, page, pageSize)

}