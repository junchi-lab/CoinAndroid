package com.junchi.coinandroid.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.junchi.coinandroid.database.entity.BookmarkEntity
import com.junchi.coinandroid.model.CoinData
import com.junchi.coinandroid.model.HistoryData
import com.junchi.coinandroid.repository.bookmark.BookmarkRepository
import com.junchi.coinandroid.repository.detail.DetailRepository
import com.junchi.coinandroid.ui.common.Event
import com.junchi.coinandroid.ui.common.EventObserver
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailViewModel(private val repository: DetailRepository,
                      private val bookmarkRepository: BookmarkRepository) : ViewModel() {
    private val _history = MutableLiveData<List<HistoryData>?>()
    val history: LiveData<List<HistoryData>?> = _history

    private val _info = MutableLiveData<CoinData?>()
    val info: LiveData<CoinData?> = _info

    private val _addBookmarkEvent = MutableLiveData<Event<Unit>>()
    val addBookmarkEvent: LiveData<Event<Unit>> = _addBookmarkEvent

    fun loadDetail(coinName: String){
        viewModelScope.launch {

            kotlin.runCatching { repository.getCoinInfo(coinName).data }
                .onSuccess { _info.value = it }
                .onFailure { Timber.e(it) }

            kotlin.runCatching { repository.getCoinHistory(coinName).data }
                .onSuccess { _history.value = it }
                .onFailure { Timber.e(it) }

        }
    }

    fun addBookmark(coinName: String){
        viewModelScope.launch {
            bookmarkRepository.addBookmark(coinName)
            _addBookmarkEvent.value = Event(Unit)
        }
    }
}