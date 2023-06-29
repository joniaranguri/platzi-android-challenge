package com.joniaranguri.platzi.android.book_details.presentation

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.joniaranguri.platzi.android.book_details.domain.model.BookDetails
import com.joniaranguri.platzi.android.book_details.domain.usecase.GetBookDetails
import com.joniaranguri.platzi.android.core.base.mvi.BaseViewState
import com.joniaranguri.platzi.android.core.network.DataState
import com.joniaranguri.platzi.android.core.testutils.BaseUnitTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException


internal class BookDetailsViewModelTest : BaseUnitTest() {


    @RelaxedMockK
    lateinit var getBookDetails: GetBookDetails

    @SpyK
    @InjectMockKs
    lateinit var viewModel: BookDetailsViewModel

    private val mockedBookId = "AO098AS"


    @Test
    fun `When LoadDetail Event is triggered, GetBookDetails useCase is called`() = runTest {
        viewModel.onTriggerEvent(BookDetailsEvent.LoadDetail(mockedBookId))

        coVerify { getBookDetails.invoke(GetBookDetails.Params(bookId = mockedBookId)) }
    }

    @Test
    fun `When GetBookDetails is successful, final state is BaseViewData`() = runTest {
        val bookDetails = mockk<BookDetails>()
        val params = GetBookDetails.Params(bookId = mockedBookId)
        coEvery { getBookDetails.invoke(params) } returns flow {
            emit(DataState.Success(bookDetails))
        }

        viewModel.onTriggerEvent(BookDetailsEvent.LoadDetail(mockedBookId))

        viewModel.uiState.test {
            awaitItem().apply {
                Truth.assertThat(this).isNotNull()
                Truth.assertThat(this).isInstanceOf(BaseViewState.Data::class.java)
            }
        }
    }

    @Test
    fun `When some error appear in LoadDetail, final state is BaseViewStateError`() = runTest {
        coEvery { getBookDetails(any()) } returns flow {
            emit(DataState.Error(IOException("some exception.")))
        }

        viewModel.onTriggerEvent(BookDetailsEvent.LoadDetail(mockedBookId))

        coVerify(exactly = 1) { getBookDetails(any()) }
        confirmVerified(getBookDetails)

        viewModel.uiState.test {
            awaitItem().apply {
                Truth.assertThat(this).isNotNull()
                Truth.assertThat(this).isInstanceOf(BaseViewState.Error::class.java)
            }
        }
    }
}