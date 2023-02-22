package com.junchi.coinandroid.network

import android.content.Context
import androidx.room.Room
import com.junchi.coinandroid.common.DATABASE_NAME
import com.junchi.coinandroid.database.AppDatabase
import com.junchi.coinandroid.repository.bookmark.BookmarkLocalDataSource
import com.junchi.coinandroid.repository.bookmark.BookmarkRepository

object ServiceLocator {

    private var apiClient: ApiClient? = null
    private var database: AppDatabase? = null
    private var bookmarkRepository: BookmarkRepository? = null


    fun provideApiClient(): ApiClient {
        return apiClient ?: kotlin.run {
            ApiClient.create().also {
                apiClient = it
            }
        }
    }

    private fun provideDatabase(applicationContext: Context): AppDatabase {
        return database ?: kotlin.run {
            Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build().also {
                database = it
            }
        }
    }

    fun provideBookmarkRepository(context: Context): BookmarkRepository {
        return bookmarkRepository ?: kotlin.run {
            val dao = provideDatabase(context.applicationContext).bookmarkDao()
            BookmarkRepository(BookmarkLocalDataSource(dao)).also {
                bookmarkRepository = it
            }
        }
    }
}