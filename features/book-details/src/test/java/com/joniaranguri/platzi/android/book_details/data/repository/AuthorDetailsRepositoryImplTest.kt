package com.joniaranguri.platzi.android.book_details.data.repository

import com.joniaranguri.platzi.android.book_details.data.local.dao.AuthorDetailsDao
import com.joniaranguri.platzi.android.book_details.data.local.model.AuthorCached
import com.joniaranguri.platzi.android.book_details.data.mapper.toDomainModel
import com.joniaranguri.platzi.android.book_details.data.remote.api.AuthorDetailsApi
import com.joniaranguri.platzi.android.book_details.data.remote.model.AuthorDetailResponse
import com.joniaranguri.platzi.android.book_details.data.repository.AuthorDetailsRepositoryImpl.Companion.PROMPT_AUTHOR_BIOGRAPHY
import com.joniaranguri.platzi.android.core.data.remote.api.OpenAIApi
import com.joniaranguri.platzi.android.core.testutils.BaseUnitTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

internal class AuthorDetailsRepositoryImplTest : BaseUnitTest() {

    @MockK(relaxed = true)
    lateinit var authorDetailsApi: AuthorDetailsApi

    @MockK(relaxed = true)
    lateinit var openAIApi: OpenAIApi

    @MockK(relaxed = true)
    lateinit var authorDetailsDao: AuthorDetailsDao

    private lateinit var repository: AuthorDetailsRepositoryImpl

    private val mockedAuthorId = "FJE2WAA9E"

    private val mockedAuthorDetailsCached = AuthorCached(
        id = mockedAuthorId,
        name = "Author number one",
        biography = "This is the description of the author",
        biographyByAI = false,
        photo = "https://some-profile-image"
    )

    override fun onCreate() {
        super.onCreate()
        repository = AuthorDetailsRepositoryImpl(authorDetailsApi, authorDetailsDao, openAIApi)
    }

    @Test
    fun `When getBookList is called, it tries to get data from Db first`() = runTest {
        val authorIds = listOf(mockedAuthorId)
        coEvery { authorDetailsDao.getAuthorDetails(authorIds) } returns listOf(
            mockedAuthorDetailsCached
        )

        val result = repository.getAuthorsDetails(authorIds)

        coVerify { authorDetailsDao.getAuthorDetails(authorIds) }
        coVerify(exactly = (0)) { authorDetailsApi.getAuthorDetails(mockedAuthorId) }
        coVerify(exactly = 0) { openAIApi.getOpenAIResult(any()) }
        Assert.assertEquals(listOf(mockedAuthorDetailsCached.toDomainModel()), result)
    }

    @Test
    fun `When getAuthorDetails is called, and DB has not data, then Api is called`() = runTest {
        val authorIds = listOf(mockedAuthorId, mockedAuthorId)
        coEvery { authorDetailsDao.getAuthorDetails(any()) } returns emptyList()

        repository.getAuthorsDetails(authorIds)

        coVerify { authorDetailsDao.getAuthorDetails(authorIds) }
        coVerify(exactly = 2) { authorDetailsApi.getAuthorDetails(mockedAuthorId) }
        coVerify(exactly = 0) { openAIApi.getOpenAIResult(any()) }
    }

    @Test
    fun `When getAuthorDetails is called, DB has not data, and api return null biography, openAIApi is called`() =
        runTest {
            val authorName = "Famous author"
            val mockedAuthorDetailsResponse =
                AuthorDetailResponse(bio = null, name = authorName)
            val authorIds = listOf(mockedAuthorId, mockedAuthorId)
            coEvery { authorDetailsDao.getAuthorDetails(any()) } returns emptyList()
            coEvery { authorDetailsApi.getAuthorDetails(mockedAuthorId) } returns mockedAuthorDetailsResponse

            repository.getAuthorsDetails(authorIds)

            coVerify { authorDetailsDao.getAuthorDetails(authorIds) }
            coVerify(exactly = 2) { authorDetailsApi.getAuthorDetails(mockedAuthorId) }
            coVerify(exactly = 2) { openAIApi.getOpenAIResult(match { it.prompt == PROMPT_AUTHOR_BIOGRAPHY + authorName }) }
        }

    @Test
    fun `When tried to call openAIApi, if author name is empty we cannot do it`() =
        runTest {
            val mockedAuthorDetailsResponse = AuthorDetailResponse(bio = null, name = "")
            val authorIds = listOf(mockedAuthorId, mockedAuthorId)
            coEvery { authorDetailsDao.getAuthorDetails(any()) } returns emptyList()
            coEvery { authorDetailsApi.getAuthorDetails(mockedAuthorId) } returns mockedAuthorDetailsResponse

            repository.getAuthorsDetails(authorIds)

            coVerify { authorDetailsDao.getAuthorDetails(authorIds) }
            coVerify(exactly = 2) { authorDetailsApi.getAuthorDetails(mockedAuthorId) }
            coVerify(exactly = 0) { openAIApi.getOpenAIResult(any()) }
        }

    @Test
    fun `When saveAuthorDetails is called, it always insert in Database`() =
        runTest {
            repository.saveAuthorDetails(mockedAuthorDetailsCached)

            coVerify { authorDetailsDao.insert(mockedAuthorDetailsCached) }
        }

}