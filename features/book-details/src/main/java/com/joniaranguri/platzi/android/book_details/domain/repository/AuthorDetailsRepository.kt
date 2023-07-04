package com.joniaranguri.platzi.android.book_details.domain.repository

import com.joniaranguri.platzi.android.book_details.data.local.model.AuthorCached
import com.joniaranguri.platzi.android.book_details.domain.model.Author

interface AuthorDetailsRepository {
    suspend fun getAuthorsDetails(authorIds: List<String>): List<Author>
    suspend fun getAIAuthorBio(authorName: String): String
    suspend fun saveAuthorDetails(authorDetails: AuthorCached)
}