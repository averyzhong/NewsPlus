package com.avery.newsplus.api.model.resp

import com.avery.newsplus.api.model.Comment

/**
 * 新闻评论相应
 *
 * @author Avery
 */

data class CommentResp(
    val code: Int,
    val msg: String,
    val data: Data
) {
    data class Data(val list: List<Comment>)
}