package com.joniaranguri.platzi.android.books.list.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = BookCached.TABLE_NAME
)
data class BookCached(

    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: String,

    @ColumnInfo(name = COLUMN_TITLE)
    val title: String,

    @ColumnInfo(name = COLUMN_PUBLISH_YEAR)
    val firstPublishYear: Int,

    @ColumnInfo(name = COLUMN_COVER_IMAGE)
    val coverImageUrl: String,

    @ColumnInfo(name = COLUMN_AUTHORS)
    val authors: String,

    @ColumnInfo(name = COLUMN_TIMESTAMP)
    val timestamp: Long
) {
    companion object {
        const val TABLE_NAME = "books"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_PUBLISH_YEAR = "first_publish_year"
        const val COLUMN_COVER_IMAGE = "cover_image_url"
        const val COLUMN_AUTHORS = "authors"
        const val COLUMN_TIMESTAMP = "timestamp"
    }
}
