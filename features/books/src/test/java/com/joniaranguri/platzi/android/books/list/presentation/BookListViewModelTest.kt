package com.joniaranguri.platzi.android.books.list.presentation

import androidx.paging.PagingData
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.joniaranguri.platzi.android.books.mocked_data.MockedData
import com.joniaranguri.platzi.android.books.list.domain.usecase.DeleteBooks
import com.joniaranguri.platzi.android.books.list.domain.usecase.GetBooks
import com.joniaranguri.platzi.android.core.base.mvi.BaseViewState
import com.joniaranguri.platzi.android.core.testutils.BaseUnitTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class BookListViewModelTest : BaseUnitTest() {

    @RelaxedMockK
    lateinit var getBooks: GetBooks

    @RelaxedMockK
    lateinit var deleteBooks: DeleteBooks

    @SpyK
    @InjectMockKs
    lateinit var viewModel: BookListViewModel

    @Test
    fun `When LoadBooks Event is triggered, GetBooks useCase is called`() = runTest {
        every { getBooks.invoke(any()) } returns flow {
            emit(PagingData.from(MockedData.getBookList()))
        }

        viewModel.onTriggerEvent(BooksEvent.LoadBooks)

        verify { getBooks.invoke(any()) }
    }

    @Test
    fun `When RefreshBooks Event is triggered, DeleteBooks useCase is called`() =
        runTest {
            viewModel.onTriggerEvent(BooksEvent.RefreshBooks)

            coVerify { deleteBooks.invoke(Unit) }
        }

    @Test
    fun `When  LoadBooks Event is triggered, The result state is BaseViewStateData`() = runTest {
        val response = PagingData.from(MockedData.getBookList())
        every { getBooks(any()) } returns flow { emit(response) }

        viewModel.onTriggerEvent(BooksEvent.LoadBooks)

        viewModel.uiState.test {
            awaitItem().apply {
                Truth.assertThat(this).isNotNull()
                Truth.assertThat(this).isInstanceOf(BaseViewState.Data::class.java)
            }
        }
    }

    @Test
    fun `When  RefreshBooks Event is triggered, LoadBooks event is dispatched and final state is BaseViewStateData`() =
        runTest {
            coEvery { deleteBooks(Unit) } returns flow { emit(Unit) }

            viewModel.onTriggerEvent(BooksEvent.RefreshBooks)

            verify { viewModel.onTriggerEvent(BooksEvent.LoadBooks) }
            viewModel.uiState.test {
                awaitItem().apply {
                    Truth.assertThat(this).isNotNull()
                    Truth.assertThat(this).isInstanceOf(BaseViewState.Data::class.java)
                }
            }
        }

}