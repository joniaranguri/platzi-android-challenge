package com.joniaranguri.platzi.android.book_details.data.repository

import androidx.annotation.VisibleForTesting
import com.joniaranguri.platzi.android.book_details.data.local.dao.AuthorDetailsDao
import com.joniaranguri.platzi.android.book_details.data.local.model.AuthorCached
import com.joniaranguri.platzi.android.book_details.data.mapper.toDomainModel
import com.joniaranguri.platzi.android.book_details.data.remote.api.AuthorDetailsApi
import com.joniaranguri.platzi.android.book_details.domain.model.Author
import com.joniaranguri.platzi.android.book_details.domain.repository.AuthorDetailsRepository
import com.joniaranguri.platzi.android.core.data.remote.api.OpenAIApi
import com.joniaranguri.platzi.android.core.data.remote.model.ai.OpenAIRequestBody
import com.joniaranguri.platzi.android.core.data.remote.model.base.InconsistentType
import javax.inject.Inject


class AuthorDetailsRepositoryImpl @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val authorDetailsApi: AuthorDetailsApi,
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val authorDetailsDao: AuthorDetailsDao,
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val openAIApi: OpenAIApi
) : AuthorDetailsRepository {

    override suspend fun getAuthorsDetails(
        authorIds: List<String>
    ): List<Author> =
        authorDetailsDao.getAuthorDetails(authorIds).map { it.toDomainModel() }.ifEmpty {
            authorIds.map { authorId ->
                authorDetailsApi
                    .getAuthorDetails(authorId)
                    .also {
                        it.id = authorId
                        if (it.bio?.value.isNullOrEmpty() && !it.name.isNullOrEmpty()) {
                            val authorAIBio = getAIAuthorBio(it.name)
                            it.bio = InconsistentType(
                                value = authorAIBio,
                                generatedByAI = authorAIBio.isNotEmpty()
                            )
                        }
                    }
                    .toDomainModel()
            }
        }

    override suspend fun getAIAuthorBio(authorName: String): String {
        return openAIApi.getOpenAIResult(
            OpenAIRequestBody(prompt = "${PROMPT_AUTHOR_BIOGRAPHY}$authorName")
        ).choices.firstOrNull()?.value.orEmpty()
    }

    override suspend fun saveAuthorDetails(authorDetails: AuthorCached) {
        authorDetailsDao.insert(authorDetails)
    }

    companion object {
        const val PROMPT_AUTHOR_BIOGRAPHY = "Generate bio for: "
    }
}
