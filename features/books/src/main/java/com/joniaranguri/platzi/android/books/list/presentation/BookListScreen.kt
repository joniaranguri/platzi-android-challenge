package com.joniaranguri.platzi.android.books.list.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.joniaranguri.platzi.android.books.list.presentation.view.BookListContent
import com.joniaranguri.platzi.android.books.list.presentation.view.BookListLoadingView
import com.joniaranguri.platzi.android.core.base.mvi.BaseViewState
import com.joniaranguri.platzi.android.core.extension.cast
import com.joniaranguri.platzi.android.ui.navigation.NavigationItem
import com.joniaranguri.platzi.android.ui.widgets.BaseErrorView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(
    goToBookDetails: (bookName: String, bookId: String, coverImage: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BookListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = NavigationItem.BookList.title) })
        }
    ) { innerPadding ->
        BookListBody(modifier, innerPadding, uiState, goToBookDetails, viewModel)
    }
}

@Composable
fun BookListBody(
    modifier: Modifier,
    innerPadding: PaddingValues,
    uiState: BaseViewState<*>,
    goToBookDetails: (bookName: String, bookId: String, coverImage: String) -> Unit,
    viewModel: BookListViewModel
) {
    when (uiState) {
        is BaseViewState.Empty -> viewModel.onTriggerEvent(BooksEvent.LoadBooks)

        is BaseViewState.Loading -> BookListLoadingView(
            modifier = modifier.padding(innerPadding),
        )

        is BaseViewState.Data -> BookListContent(
            modifier = modifier.padding(innerPadding),
            viewState = uiState.cast<BaseViewState.Data<BooksViewState>>().value,
            goToBookDetails = goToBookDetails,
            paddingValues = innerPadding,
            viewModel = viewModel
        )

        is BaseViewState.Error -> BaseErrorView(
            e = uiState.cast<BaseViewState.Error>().throwable,
            action = {
                viewModel.onTriggerEvent(BooksEvent.LoadBooks)
            }
        )
    }
}
