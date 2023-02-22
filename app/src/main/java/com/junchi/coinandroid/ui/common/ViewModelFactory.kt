package com.junchi.coinandroid.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.junchi.coinandroid.network.ServiceLocator
import com.junchi.coinandroid.repository.detail.DetailRemoteDataSource
import com.junchi.coinandroid.repository.detail.DetailRepository
import com.junchi.coinandroid.repository.home.HomeRemoteDataSource
import com.junchi.coinandroid.repository.home.HomeRepository
import com.junchi.coinandroid.repository.status.StatusRemoteDataSource
import com.junchi.coinandroid.repository.status.StatusRepository
import com.junchi.coinandroid.ui.bookmark.BookmarkViewModel
import com.junchi.coinandroid.ui.detail.DetailViewModel
import com.junchi.coinandroid.ui.home.HomeViewModel
import com.junchi.coinandroid.ui.status.StatusViewModel

class ViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {

            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {

                val repository = HomeRepository(HomeRemoteDataSource(ServiceLocator.provideApiClient()))
                HomeViewModel(repository) as T

            }

            modelClass.isAssignableFrom(StatusViewModel::class.java) -> {
                val repository = StatusRepository(StatusRemoteDataSource(ServiceLocator.provideApiClient()))
                StatusViewModel(repository) as T
            }

            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                val repository = DetailRepository(DetailRemoteDataSource(ServiceLocator.provideApiClient()))
                DetailViewModel(repository, ServiceLocator.provideBookmarkRepository(context)) as T
            }

            modelClass.isAssignableFrom(BookmarkViewModel::class.java) -> {
                BookmarkViewModel(ServiceLocator.provideBookmarkRepository(context)) as T
            }

            else -> {
                throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}