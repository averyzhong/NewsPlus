package com.avery.newsplus.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.avery.newsplus.App
import com.avery.newsplus.db.dao.FavoriteDao
import com.avery.newsplus.db.entity.Favorite

/**
 * 数据库
 *
 * @author Avery
 */

@Database(entities = [Favorite::class], version = 1, exportSchema = true)
abstract class AppDataBase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    companion object {
        private const val DB_NAME = "news.db"

        private val instance: AppDataBase by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Room.databaseBuilder(
                App.instance(),
                AppDataBase::class.java,
                DB_NAME
            ).build()
        }

        fun instance() = instance
    }
}