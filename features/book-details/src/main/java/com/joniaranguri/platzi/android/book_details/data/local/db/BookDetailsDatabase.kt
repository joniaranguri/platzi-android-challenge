package com.joniaranguri.platzi.android.book_details.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joniaranguri.platzi.android.book_details.data.local.dao.AuthorDetailsDao
import com.joniaranguri.platzi.android.book_details.data.local.dao.BookDetailsDao
import com.joniaranguri.platzi.android.book_details.data.local.model.AuthorCached
import com.joniaranguri.platzi.android.book_details.data.local.model.BookDetailsCached

@Database(
    entities = [BookDetailsCached::class, AuthorCached::class],
    version = 1,
    exportSchema = false
)
abstract class BookDetailsDatabase : RoomDatabase() {
    abstract fun bookDetailsDao(): BookDetailsDao
    abstract fun authorDetailsDao(): AuthorDetailsDao

}