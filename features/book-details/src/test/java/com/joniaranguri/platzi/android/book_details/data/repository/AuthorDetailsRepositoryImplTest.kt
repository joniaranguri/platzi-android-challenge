package com.joniaranguri.platzi.android.book_details.data.repository

import com.joniaranguri.platzi.android.book_details.data.local.dao.AuthorDetailsDao
import com.joniaranguri.platzi.android.book_details.data.local.model.AuthorCached
import com.joniaranguri.platzi.android.book_details.data.mapper.toDomainModel
import com.joniaranguri.platzi.android.book_details.data.remote.api.AuthorDetailsApi
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
    lateinit var authorDetailsDao: AuthorDetailsDao

    private lateinit var repository: AuthorDetailsRepositoryImpl

    private val mockedAuthorId = "FJE2WAA9E"

    private val mockedAuthorDetailsCached = AuthorCached(
        id = mockedAuthorId,
        name = "Author number one",
        biography = "This is the description of the author",
        photo = "https://some-profile-image"
    )

    override fun onCreate() {
        super.onCreate()
        repository = AuthorDetailsRepositoryImpl(authorDetailsApi, authorDetailsDao)
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
        Assert.assertEquals(listOf(mockedAuthorDetailsCached.toDomainModel()), result)
    }

    @Test
    fun `When getAuthorDetails is called, and DB has not data, then Api is called`() = runTest {
        val authorIds = listOf(mockedAuthorId, mockedAuthorId)
        coEvery { authorDetailsDao.getAuthorDetails(any()) } returns emptyList()

        repository.getAuthorsDetails(authorIds)

        coVerify { authorDetailsDao.getAuthorDetails(authorIds) }
        coVerify(exactly = 2) { authorDetailsApi.getAuthorDetails(mockedAuthorId) }
    }


    @Test
    fun `When saveAuthorDetails is called, it always insert in Database`() =
        runTest {
            repository.saveAuthorDetails(mockedAuthorDetailsCached)

            coVerify { authorDetailsDao.insert(mockedAuthorDetailsCached) }
        }

}