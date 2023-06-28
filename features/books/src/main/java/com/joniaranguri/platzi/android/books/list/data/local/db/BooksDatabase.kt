package com.joniaranguri.platzi.android.books.list.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joniaranguri.platzi.android.books.list.data.local.dao.BooksDao
import com.joniaranguri.platzi.android.books.list.data.local.model.BookCached

@Database(
    entities = [BookCached::class],
    version = 1,
    exportSchema = false
)
abstract class BooksDatabase : RoomDatabase() {
    abstract fun booksDao(): BooksDao

}