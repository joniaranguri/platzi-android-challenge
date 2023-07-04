package com.joniaranguri.platzi.android.book_details.data.repository

import com.joniaranguri.platzi.android.book_details.data.local.dao.BookDetailsDao
import com.joniaranguri.platzi.android.book_details.data.local.model.BookDetailsCached
import com.joniaranguri.platzi.android.book_details.data.mapper.toDomainModel
import com.joniaranguri.platzi.android.book_details.data.remote.api.BookDetailsApi
import com.joniaranguri.platzi.android.book_details.data.remote.model.BookDetailsResponse
import com.joniaranguri.platzi.android.book_details.data.repository.BookDetailsRepositoryImpl.Companion.PROMPT_BOOK_DESCRIPTION
import com.joniaranguri.platzi.android.core.data.remote.api.OpenAIApi
import com.joniaranguri.platzi.android.core.data.remote.model.base.InconsistentType
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
    lateinit var openAIApi: OpenAIApi

    @MockK(relaxed = true)
    lateinit var bookDetailsDao: BookDetailsDao

    private lateinit var repository: BookDetailsRepositoryImpl

    private val mockedBookId = "FJE29E"
    private val mockedBookTitle = "Some title"

    private val mockedBookDetailsCached = BookDetailsCached(
        id = mockedBookId,
        description = "Some awesome book",
        descriptionByAI = false,
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
        repository = BookDetailsRepositoryImpl(bookDetailsApi, bookDetailsDao, openAIApi)
    }

    @Test
    fun `When getBookList is called, it tries to get data from Db first`() = runTest {
        coEvery { bookDetailsDao.getBookDetails(mockedBookId) } returns mockedBookDetailsCached

        val result = repository.getBookDetails(mockedBookId, mockedBookTitle)

        coVerify { bookDetailsDao.getBookDetails(mockedBookId) }
        coVerify(exactly = (0)) { bookDetailsApi.getBookDetails(mockedBookId) }
        coVerify(exactly = (0)) { openAIApi.getOpenAIResult(any()) }
        Assert.assertEquals(mockedBookDetailsCached.toDomainModel(), result)
    }

    @Test
    fun `When getBookList is called, and DB has not the data, then Api is called`() = runTest {
        val bookDetailsResponseMocked =
            BookDetailsResponse(description = InconsistentType(value = "Some book description"))
        coEvery { bookDetailsDao.getBookDetails(mockedBookId) } returns null
        coEvery { bookDetailsApi.getBookDetails(mockedBookId) } returns bookDetailsResponseMocked

        repository.getBookDetails(mockedBookId, mockedBookTitle)

        coVerify { bookDetailsDao.getBookDetails(mockedBookId) }
        coVerify { bookDetailsApi.getBookDetails(mockedBookId) }
        coVerify(exactly = (0)) { openAIApi.getOpenAIResult(any()) }
    }

    @Test
    fun `When getBookList is called, DB has not the data and api return null description, openAIApi is called`() =
        runTest {
            val bookDetailsResponseMocked = BookDetailsResponse(description = null)
            coEvery { bookDetailsDao.getBookDetails(mockedBookId) } returns null
            coEvery { bookDetailsApi.getBookDetails(mockedBookId) } returns bookDetailsResponseMocked

            repository.getBookDetails(mockedBookId, mockedBookTitle)

            coVerify { bookDetailsDao.getBookDetails(mockedBookId) }
            coVerify { bookDetailsApi.getBookDetails(mockedBookId) }
            coVerify { openAIApi.getOpenAIResult(match { it.prompt == PROMPT_BOOK_DESCRIPTION + mockedBookTitle }) }
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