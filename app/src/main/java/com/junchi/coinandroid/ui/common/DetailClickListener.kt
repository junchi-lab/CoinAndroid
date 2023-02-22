package com.junchi.coinandroid.ui.common

import com.junchi.coinandroid.database.entity.BookmarkEntity

interface DetailClickListener {
    fun onDetailClick(coinName: String)
}

interface DeleteClickListener {
    fun onDeleteClick(bookmark: BookmarkEntity)
}