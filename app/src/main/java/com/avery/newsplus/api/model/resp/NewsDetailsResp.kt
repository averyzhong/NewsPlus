package com.avery.newsplus.api.model.resp

import com.avery.newsplus.api.model.NewsDetail

class NewsDetailsResp(
    val code: Int,
    val msg: String,
    val data: NewsDetail
)