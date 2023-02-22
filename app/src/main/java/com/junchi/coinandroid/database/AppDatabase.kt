package com.junchi.coinandroid.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.junchi.coinandroid.database.dao.BookmarkDao
import com.junchi.coinandroid.database.entity.BookmarkEntity

@Database(entities = [BookmarkEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
}