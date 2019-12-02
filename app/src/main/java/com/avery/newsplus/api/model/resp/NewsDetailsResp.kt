package com.avery.newsplus.api.model.resp

import com.avery.newsplus.api.model.NewsDetail

/**
 * 新闻详情相应
 *
 * @author Avery
 */

data class NewsDetailsResp(
    val code: Int,
    val msg: String,
    val data: NewsDetail
)