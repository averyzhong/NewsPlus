package com.avery.newsplus.api.model.resp

import com.avery.newsplus.api.model.Comment

class CommentResp(
    val code: Int,
    val msg: String,
    val data: Data
) {
    data class Data(val list: List<Comment>)
}