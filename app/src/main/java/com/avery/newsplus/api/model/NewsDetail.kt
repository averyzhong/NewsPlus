package com.avery.newsplus.api.model

import com.google.gson.annotations.SerializedName

data class NewsDetail(
    val aid: String?,
    val title: String?,
    @SerializedName("headpic")
    val headPic: String?,
    val writer: String?,
    val source: String?,
    @SerializedName("source_url")
    val sourceUrl: String?,
    @SerializedName("reply_count")
    val replyCount: Int = 0,
    @SerializedName("click_count")
    val clickCount: Int = 0,
    @SerializedName("pub_time")
    val publishTime: Long = 0,
    val summary: String?,
    val content: String?,
    @SerializedName("imglist")
    val imgList: String?,
    @SerializedName("taglist")
    val taglist: String?
)