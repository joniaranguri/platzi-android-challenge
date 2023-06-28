package com.joniaranguri.platzi.android.book_details.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = BookDetailsCached.TABLE_NAME
)
data class BookDetailsCached(

    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: String,

    @ColumnInfo(name = COLUMN_DESCRIPTION)
    val description: String,

    @ColumnInfo(name = COLUMN_SUBJECTS)
    val subjects: String,

    @ColumnInfo(name = COLUMN_AUTHORS)
    val authors: String,

    @ColumnInfo(name = COLUMN_RATING_AVG)
    val ratingAverage: Float,

    @ColumnInfo(name = COLUMN_RATING_COUNT)
    val ratingCount: Int,

    @ColumnInfo(name = COLUMN_WANT_READ)
    val wantToRead: Int,

    @ColumnInfo(name = COLUMN_CURRENTLY_READING)
    val currentlyReading: Int,

    @ColumnInfo(name = COLUMN_ALREADY_READ)
    val alreadyRead: Int

) {
    companion object {
        const val TABLE_NAME = "book_details"
        const val COLUMN_ID = "id"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_SUBJECTS = "subjects"
        const val COLUMN_AUTHORS = "authors"
        const val COLUMN_RATING_AVG = "rating_average"
        const val COLUMN_RATING_COUNT = "rating_count"
        const val COLUMN_WANT_READ = "want_to_read"
        const val COLUMN_CURRENTLY_READING = "currently_reading"
        const val COLUMN_ALREADY_READ = "already_read"
    }
}
