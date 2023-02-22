package com.junchi.coinandroid.repository.bookmark

import com.junchi.coinandroid.database.dao.BookmarkDao
import com.junchi.coinandroid.database.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

class BookmarkLocalDataSource(private val dao: BookmarkDao) : IBookmarkDataSource {
    override suspend fun addBookmark(bookmarkEntity: BookmarkEntity) {
        dao.insert(bookmarkEntity)
    }

    override fun getAllBookmark(): Flow<List<BookmarkEntity>> {
        return  dao.getAllData()
    }

    override suspend fun deleteBookmark(bookmarkEntity: BookmarkEntity) {
        dao.delete(bookmarkEntity)
    }

}