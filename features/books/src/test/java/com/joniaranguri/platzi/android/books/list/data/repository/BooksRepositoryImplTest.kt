package com.joniaranguri.platzi.android.books.list.data.repository

import com.joniaranguri.platzi.android.books.list.data.local.dao.BooksDao
import com.joniaranguri.platzi.android.books.list.data.remote.api.BooksApi
import com.joniaranguri.platzi.android.core.testutils.BaseUnitTest
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

internal class BooksRepositoryImplTest : BaseUnitTest() {

    @MockK(relaxed = true)
    lateinit var booksApi: BooksApi

    @MockK(relaxed = true)
    lateinit var booksDao: BooksDao

    private lateinit var repository: BooksRepositoryImpl

    override fun onCreate() {
        super.onCreate()
        repository = BooksRepositoryImpl(booksApi, booksDao)
    }

    @Test
    fun `When getBookList is called, Always call booksDao and no call booksApi`() = runTest {
        val pageValue = 1
        val loadSizeValue = 10
        val limit = slot<Int>()
        val offset = slot<Int>()

        repository.getBookList(page = pageValue, loadSize = loadSizeValue)

        coVerify { booksDao.getBookList(offset = capture(offset), limit = capture(limit)) }
        coVerify(exactly = (0)) { booksApi.getBookList(limit = any(), page = any()) }
        Assert.assertEquals(loadSizeValue, limit.captured)
        Assert.assertEquals(loadSizeValue * (pageValue - 1), offset.captured)
    }


    @Test
    fun `When refreshBooks is called on Repository, booksDao deleteBooks is also called`() =
        runTest {
            repository.deleteBooks()

            coVerify { booksDao.deleteBooks() }
        }

    @Test
    fun `When refreshBooks is called, call booksApi, save in DB and return result from DB`() =
        runTest {
            val pageValue = 1
            val loadSizeValue = 10
            val page = slot<Int>()
            val loadSize = slot<Int>()

            repository.refreshBooks(page = pageValue, loadSize = loadSizeValue)

            coVerify { booksApi.getBookList(page = capture(page), limit = capture(loadSize)) }
            coVerify(exactly = (1)) { booksDao.insertOnlyNews(any()) }
            coVerify { booksDao.getBookList(offset = any(), limit = any()) }

            Assert.assertEquals(pageValue, page.captured)
            Assert.assertEquals(loadSizeValue, loadSize.captured)
        }
}