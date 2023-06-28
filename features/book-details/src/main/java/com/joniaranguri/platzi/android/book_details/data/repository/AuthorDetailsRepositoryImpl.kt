package com.joniaranguri.platzi.android.book_details.data.repository

import androidx.annotation.VisibleForTesting
import com.joniaranguri.platzi.android.book_details.data.local.dao.AuthorDetailsDao
import com.joniaranguri.platzi.android.book_details.data.local.model.AuthorCached
import com.joniaranguri.platzi.android.book_details.data.mapper.toDomainModel
import com.joniaranguri.platzi.android.book_details.data.remote.api.AuthorDetailsApi
import com.joniaranguri.platzi.android.book_details.domain.model.Author
import com.joniaranguri.platzi.android.book_details.domain.repository.AuthorDetailsRepository
import javax.inject.Inject


class AuthorDetailsRepositoryImpl @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val authorDetailsApi: AuthorDetailsApi,
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val authorDetailsDao: AuthorDetailsDao
) : AuthorDetailsRepository {

    override suspend fun getAuthorsDetails(
        authorIds: List<String>
    ): List<Author> =
        authorDetailsDao.getAuthorDetails(authorIds).map { it.toDomainModel() }.ifEmpty {
            authorIds.map { authorId ->
                authorDetailsApi
                    .getAuthorDetails(authorId)
                    .also { it.id = authorId }
                    .toDomainModel()
            }
        }

    override suspend fun saveAuthorDetails(authorDetails: AuthorCached) {
        authorDetailsDao.insert(authorDetails)
    }
}
