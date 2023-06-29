package com.joniaranguri.platzi.android.books.list.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.joniaranguri.platzi.android.books.list.data.local.model.BookCached
import com.joniaranguri.platzi.android.core.room.dao.BaseDao

@Dao
interface BooksDao : BaseDao<BookCached> {

    @Query("SELECT * FROM ${BookCached.TABLE_NAME} ORDER BY timestamp ASC, title LIMIT :limit OFFSET :offset")
    suspend fun getBookList(limit: Int, offset: Int): List<BookCached>

    @Query("DELETE FROM ${BookCached.TABLE_NAME}")
    suspend fun deleteBooks()
}
