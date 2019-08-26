package com.avery.newsplus.api

import com.avery.newsplus.api.model.resp.CommentResp
import com.avery.newsplus.api.model.resp.NewsDetailsResp
import com.avery.newsplus.api.model.resp.NewsMetaResp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("/news/nlist")
    suspend fun getNewsList(
        @Query("cid") categoryId: Int,
        @Query("page") page: Int,
        @Query("psize") pageSize: Int
    ): Response<NewsMetaResp>

    @GET("/news/ndetail")
    suspend fun getNewsDetails(
        @Query("aid") newsId: String
    ): Response<NewsDetailsResp>

    @GET("/news/nreply")
    suspend fun getNewsComments(
        @Query("aid") newsId: String,
        @Query("page") page: Int,
        @Query("psize") pageSize: Int
    ): Response<CommentResp>
}