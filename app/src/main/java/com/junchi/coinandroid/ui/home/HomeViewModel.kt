package com.junchi.coinandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.junchi.coinandroid.model.Coin
import com.junchi.coinandroid.repository.home.HomeRepository
import com.junchi.coinandroid.ui.common.Event
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(private val repository: HomeRepository): ViewModel() {

    private val _home = MutableLiveData<List<Coin>>()
    val home: LiveData<List<Coin>> = _home

    init {
        loadHome()
    }

    private fun loadHome() {
        viewModelScope.launch {

            kotlin.runCatching { repository.getCoinInfo() }
                .onSuccess { _home.value = it }
                .onFailure { Timber.e(it) }

        }
    }
}