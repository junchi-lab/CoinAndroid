package com.junchi.coinandroid.repository.bookmark

import com.junchi.coinandroid.database.entity.BookmarkEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookmarkRepository(
    private val localDataSource: BookmarkLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
){

    suspend fun addBookmark(coinName: String){
        withContext(ioDispatcher){
            val bookmark = BookmarkEntity(
                0,
                coinName,
                System.currentTimeMillis()
            )
            localDataSource.addBookmark(bookmark)
        }
    }

    suspend fun deleteBookmark(bookmark: BookmarkEntity){
        withContext(ioDispatcher){
            localDataSource.deleteBookmark(bookmark)
        }
    }

    fun getBookmark() = localDataSource.getAllBookmark()

}