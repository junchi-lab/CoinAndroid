package com.junchi.coinandroid.repository.bookmark

import com.junchi.coinandroid.database.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

interface IBookmarkDataSource {
    suspend fun addBookmark(bookmarkEntity: BookmarkEntity)
    fun getAllBookmark(): Flow<List<BookmarkEntity>>
    suspend fun deleteBookmark(bookmarkEntity: BookmarkEntity)
}