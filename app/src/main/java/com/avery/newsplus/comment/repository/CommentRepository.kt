package com.avery.newsplus.comment.repository

import com.avery.newsplus.api.NewsService

class CommentRepository(
    private val api: NewsService
) {

    suspend fun getNewsComments(
        newsId: String,
        page: Int,
        pageSize: Int
    ) = api.getNewsComments(newsId, page, pageSize)
}