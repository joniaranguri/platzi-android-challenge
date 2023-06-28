package com.joniaranguri.platzi.android.book_details.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.joniaranguri.platzi.android.book_details.data.local.model.BookDetailsCached
import com.joniaranguri.platzi.android.core.room.dao.BaseDao


@Dao
interface BookDetailsDao : BaseDao<BookDetailsCached> {

    @Query("SELECT * FROM ${BookDetailsCached.TABLE_NAME} WHERE id = :bookId")
    suspend fun getBookDetails(bookId: String): BookDetailsCached?

}
