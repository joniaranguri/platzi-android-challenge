package com.joniaranguri.platzi.android.book_details.data.repository

import com.joniaranguri.platzi.android.book_details.data.local.dao.BookDetailsDao
import com.joniaranguri.platzi.android.book_details.data.local.model.BookDetailsCached
import com.joniaranguri.platzi.android.book_details.data.mapper.toDomainModel
import com.joniaranguri.platzi.android.book_details.data.remote.api.BookDetailsApi
import com.joniaranguri.platzi.android.core.testutils.BaseUnitTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test


internal class BookDetailsRepositoryImplTest : BaseUnitTest() {

    @MockK(relaxed = true)
    lateinit var bookDetailsApi: BookDetailsApi

    @MockK(relaxed = true)
    lateinit var bookDetailsDao: BookDetailsDao

    private lateinit var repository: BookDetailsRepositoryImpl

    private val mockedBookId = "FJE29E"

    private val mockedBookDetailsCached = BookDetailsCached(
        id = mockedBookId,
        description = "Some awesome book",
        subjects = "Topic one|Topic two",
        authors = "Author one|Author two",
        ratingAverage = 3.4f,
        ratingCount = 90,
        wantToRead = 200,
        currentlyReading = 20,
        alreadyRead = 90
    )

    override fun onCreate() {
        super.onCreate()
        repository = BookDetailsRepositoryImpl(bookDetailsApi, bookDetailsDao)
    }

    @Test
    fun `When getBookList is called, it tries to get data from Db first`() = runTest {
        coEvery { bookDetailsDao.getBookDetails(mockedBookId) } returns mockedBookDetailsCached

        val result = repository.getBookDetails(mockedBookId)

        coVerify { bookDetailsDao.getBookDetails(mockedBookId) }
        coVerify(exactly = (0)) { bookDetailsApi.getBookDetails(mockedBookId) }
        Assert.assertEquals(mockedBookDetailsCached.toDomainModel(), result)
    }

    @Test
    fun `When getBookList is called, and DB has not the data, then Api is called`() = runTest {
        coEvery { bookDetailsDao.getBookDetails(mockedBookId) } returns null

        repository.getBookDetails(mockedBookId)

        coVerify { bookDetailsDao.getBookDetails(mockedBookId) }
        coVerify { bookDetailsApi.getBookDetails(mockedBookId) }
    }


    @Test
    fun `When getBookRatings is called, it always calls bookDetailsApi`() =
        runTest {
            repository.getBookRatings(mockedBookId)

            coVerify { bookDetailsApi.getBookRatings(mockedBookId) }
        }

    @Test
    fun `When getBookAnalytics is called, it always calls bookDetailsApi`() =
        runTest {
            repository.getBookAnalytics(mockedBookId)

            coVerify { bookDetailsApi.getBookAnalytics(mockedBookId) }
        }

}