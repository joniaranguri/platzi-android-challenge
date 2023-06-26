package com.joniaranguri.platzi.android.book_details.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(
    tableName = "book_details"
)
data class BookDetailsCached(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "subjects")
    val subjects: String,

    @ColumnInfo(name = "authors")
    val authors: String,

    @ColumnInfo(name = "rating_average")
    val ratingAverage: Float,

    @ColumnInfo(name = "rating_count")
    val ratingCount: Int,

    @ColumnInfo(name = "want_to_read")
    val wantToRead: Int,

    @ColumnInfo(name = "currently_reading")
    val currentlyReading: Int,

    @ColumnInfo(name = "already_read")
    val alreadyRead: Int

)
