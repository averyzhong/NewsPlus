package com.avery.newsplus.db.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.avery.newsplus.db.entity.Favorite

/**
 * 收藏DAO
 *
 * @author Avery
 */

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(favorite: Favorite)

    @Query("DELETE FROM t_favorite WHERE news_id = :newsId")
    suspend fun removeById(newsId: String): Int

    @Query("SELECT * FROM t_favorite WHERE news_id = :newsId")
    suspend fun queryById(newsId: String): Favorite?

    @Query("SELECT * FROM t_favorite WHERE news_id = :newsId")
     fun queryById2(newsId: String): LiveData<Favorite>

}