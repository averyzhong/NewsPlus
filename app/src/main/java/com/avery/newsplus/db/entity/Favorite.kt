package com.avery.newsplus.db.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 收藏实体类
 *
 * @author Avery
 */

@Entity(tableName = "t_favorite")
class Favorite {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "news_id")
    var newsId: String? = ""
    var title: String? = ""
    var source: String? = ""
    @ColumnInfo(name = "comment_count")
    var commentCount: Int? = 0
    @ColumnInfo(name = "publish_date")
    var publishDate: Long? = 0
    @ColumnInfo(name = "header_image_url")
    var headerImageUrl: String? = ""
}