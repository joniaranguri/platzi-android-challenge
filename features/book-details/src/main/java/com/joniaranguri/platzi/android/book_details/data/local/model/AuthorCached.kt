package com.joniaranguri.platzi.android.book_details.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = AuthorCached.TABLE_NAME
)
data class AuthorCached(

    @PrimaryKey
    @ColumnInfo(name = COLUMN_ID)
    val id: String,

    @ColumnInfo(name = COLUMN_NAME)
    val name: String,

    @ColumnInfo(name = COLUMN_BIOGRAPHY)
    val biography: String,

    @ColumnInfo(name = COLUMN_PHOTO)
    val photo: String
) {
    companion object {
        const val TABLE_NAME = "author"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_BIOGRAPHY = "biography"
        const val COLUMN_PHOTO = "photo"
    }
}
