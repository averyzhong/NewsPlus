package com.avery.newsplus.details.repository

import com.avery.newsplus.api.NewsService
import com.avery.newsplus.db.dao.FavoriteDao
import com.avery.newsplus.db.entity.Favorite

/**
 * 新闻详情数据仓库
 *
 * @author Avery
 */

class NewsDetailRepository(
    private val api: NewsService,
    private val favoriteDao: FavoriteDao
) {

    suspend fun getNewsDetails(newsId: String) = api.getNewsDetails(newsId)

    suspend fun addFavorite(favorite: Favorite) = favoriteDao.add(favorite)

    suspend fun removeFavorite(newsId: String) = favoriteDao.removeById(newsId)

    suspend fun queryFavorite(newsId: String) = favoriteDao.queryById(newsId)
}