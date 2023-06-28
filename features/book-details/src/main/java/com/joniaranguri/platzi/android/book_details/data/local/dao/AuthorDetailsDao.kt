package com.joniaranguri.platzi.android.book_details.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.joniaranguri.platzi.android.book_details.data.local.model.AuthorCached
import com.joniaranguri.platzi.android.core.room.dao.BaseDao

@Dao
interface AuthorDetailsDao : BaseDao<AuthorCached> {

    @Query("SELECT * FROM ${AuthorCached.TABLE_NAME} WHERE id IN (:authorIds)")
    suspend fun getAuthorDetails(authorIds: List<String>): List<AuthorCached>
}
