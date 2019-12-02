package com.avery.newsplus.api.model.resp

import com.avery.newsplus.api.model.NewsMetaItem

/**
 * 新闻列表响应
 *
 * @author Avery
 */

data class NewsMetaResp(
    val code: Int,
    val data: Data
) {

    data class Data(val list: List<NewsMetaItem>)
}