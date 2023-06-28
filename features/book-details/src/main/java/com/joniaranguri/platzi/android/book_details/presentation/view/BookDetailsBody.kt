package com.joniaranguri.platzi.android.book_details.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.joniaranguri.platzi.android.book_details.presentation.BookDetailsEvent
import com.joniaranguri.platzi.android.book_details.presentation.BookDetailsViewModel
import com.joniaranguri.platzi.android.book_details.presentation.BookDetailsViewState
import com.joniaranguri.platzi.android.core.base.mvi.BaseViewState
import com.joniaranguri.platzi.android.core.extension.cast
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

    when (uiState) {
        is BaseViewState.Data -> BookDetailsContent(
            modifier = modifier,
            title = title,
            coverImageUrl = coverImageUrl,
            bookDetailsViewState = uiState.cast<BaseViewState.Data<BookDetailsViewState>>().value,
        )

        is BaseViewState.Empty -> Text(text = "Empty")
        is BaseViewState.Error -> Text(text = "Empty") /*ErrorView(
            e = uiState.cast<BaseViewState.Error>().throwable,
            action = {
                viewModel.onTriggerEvent(CharacterDetailEvent.LoadDetail(id))
            }
        )*/
        is BaseViewState.Loading -> Text(text = "Loading")
    }

    LaunchedEffect(key1 = viewModel, block = {
        viewModel.onTriggerEvent(BookDetailsEvent.LoadDetail(bookId))
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
