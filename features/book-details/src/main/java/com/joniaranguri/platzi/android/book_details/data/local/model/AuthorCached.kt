package com.joniaranguri.platzi.android.book_details.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "author"
)
data class AuthorCached(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "biography")
    val biography: String,

    @ColumnInfo(name = "photo")
    val photo: String
)
