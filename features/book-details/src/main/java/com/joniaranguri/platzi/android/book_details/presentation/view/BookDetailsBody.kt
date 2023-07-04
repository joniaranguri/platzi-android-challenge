package com.joniaranguri.platzi.android.book_details.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.joniaranguri.platzi.android.book_details.presentation.BookDetailsEvent
import com.joniaranguri.platzi.android.book_details.presentation.BookDetailsViewModel
import com.joniaranguri.platzi.android.book_details.presentation.BookDetailsViewState
import com.joniaranguri.platzi.android.core.base.mvi.BaseViewState
import com.joniaranguri.platzi.android.core.extension.cast
import com.joniaranguri.platzi.android.ui.widgets.BaseErrorView
import com.joniaranguri.platzi.android.ui.widgets.TitleLarge


@Composable
fun BookDetailsBody(
    bookId: String,
    title: String,
    coverImageUrl: String,
    modifier: Modifier,
    viewModel: BookDetailsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val initialLoad = rememberSaveable { true }

    when (uiState) {
        is BaseViewState.Data -> BookDetailsContent(
            modifier = modifier,
            title = title,
            coverImageUrl = coverImageUrl,
            bookDetailsViewState = uiState.cast<BaseViewState.Data<BookDetailsViewState>>().value,
        )

        is BaseViewState.Error -> BaseErrorView(
            e = uiState.cast<BaseViewState.Error>().throwable,
            action = {
                viewModel.onTriggerEvent(BookDetailsEvent.LoadDetail(bookId, title))
            }
        )

        is BaseViewState.Loading, BaseViewState.Empty -> LoadingBookDetailView(modifier)
    }

    LaunchedEffect(key1 = initialLoad, block = {
        viewModel.onTriggerEvent(BookDetailsEvent.LoadDetail(bookId, title))
    })
}

@Composable
fun BookDetailsContent(
    bookDetailsViewState: BookDetailsViewState,
    coverImageUrl: String,
    title: String,
    modifier: Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        bookDetailsViewState.bookDetails?.let { bookDetails ->

            FlipDetails(bookDetails, title, coverImageUrl)

            if (bookDetails.subjects.isNotEmpty()) {
                TitleLarge(text = "Topics")
                SubjectList(subjectList = bookDetails.subjects)
            }
            if (bookDetails.authors.isNotEmpty()) {
                TitleLarge(text = "About the authors")
                AuthorList(authorList = bookDetails.authors)
            }
        }
    }

}
