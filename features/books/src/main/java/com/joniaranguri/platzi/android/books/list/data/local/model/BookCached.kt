package com.joniaranguri.platzi.android.books.list.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "books"
)
data class BookCached(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "first_publish_year")
    val firstPublishYear: Int,

    @ColumnInfo(name = "cover_image_url")
    val coverImageUrl: String,

    @ColumnInfo(name = "author_name")
    val authorName: List<String>

)
