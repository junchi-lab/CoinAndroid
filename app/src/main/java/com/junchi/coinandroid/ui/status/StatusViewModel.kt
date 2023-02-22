package com.junchi.coinandroid.ui.status

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junchi.coinandroid.model.CoinStatus
import com.junchi.coinandroid.repository.status.StatusRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class StatusViewModel(private val repository: StatusRepository): ViewModel() {

    private val _status = MutableLiveData<List<CoinStatus>>()
    val status: LiveData<List<CoinStatus>> = _status

    init {
        loadStatus()
    }

    private fun loadStatus(){
        viewModelScope.launch {

            kotlin.runCatching { repository.getCoinStatus() }
                .onSuccess { _status.value = it }
                .onFailure { Timber.e(it) }

        }
    }
}