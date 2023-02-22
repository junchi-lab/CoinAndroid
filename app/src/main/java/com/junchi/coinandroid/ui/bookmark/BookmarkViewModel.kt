package com.junchi.coinandroid.ui.bookmark

import androidx.lifecycle.*
import com.junchi.coinandroid.database.entity.BookmarkEntity
import com.junchi.coinandroid.repository.bookmark.BookmarkRepository
import com.junchi.coinandroid.ui.common.Event
import kotlinx.coroutines.launch

class BookmarkViewModel(private val repository: BookmarkRepository): ViewModel() {
    lateinit var bookmark : LiveData<List<BookmarkEntity>>

    init {
        loadBookmark()
    }

    private fun loadBookmark() {
        viewModelScope.launch {
            val bookmarkList = repository.getBookmark().asLiveData()
            bookmark = bookmarkList
        }
    }

    fun deleteBookmark(bookmark: BookmarkEntity){
        viewModelScope.launch {
            repository.deleteBookmark(bookmark)
        }
    }
}