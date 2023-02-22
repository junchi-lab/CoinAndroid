package com.junchi.coinandroid.database.dao

import androidx.room.*
import com.junchi.coinandroid.common.BOOKMARK_TABLE
import com.junchi.coinandroid.database.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    @Query("SELECT * FROM $BOOKMARK_TABLE")
    fun getAllData() : Flow<List<BookmarkEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(bookmark: BookmarkEntity)

    @Delete
    fun delete(bookmark: BookmarkEntity)
}