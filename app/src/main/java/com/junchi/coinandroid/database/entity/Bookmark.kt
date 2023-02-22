package com.junchi.coinandroid.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark_table")
data class BookmarkEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "coin_id") val coinId: String,
    val date: Long
)

