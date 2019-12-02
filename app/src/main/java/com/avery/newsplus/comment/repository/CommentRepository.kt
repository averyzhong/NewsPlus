package com.avery.newsplus.comment.repository

import com.avery.newsplus.api.NewsService

/**
 * 新闻评论数据仓库
 *
 * @author Avery
 */

class CommentRepository(
    private val api: NewsService
) {

    suspend fun getNewsComments(
        newsId: String,
        page: Int,
        pageSize: Int
    ) = api.getNewsComments(newsId, page, pageSize)
}