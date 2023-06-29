package com.joniaranguri.platzi.android.books.list.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.joniaranguri.platzi.android.books.list.presentation.BookListViewModel
import com.joniaranguri.platzi.android.books.list.presentation.BooksEvent
import com.joniaranguri.platzi.android.books.list.presentation.BooksViewState
import com.joniaranguri.platzi.android.core.composable.rememberFlowWithLifecycle
import com.joniaranguri.platzi.android.ui.ext.defaultPadding
import com.joniaranguri.platzi.android.ui.ext.defaultScreenPadding

@Composable
fun BookListContent(
    modifier: Modifier,
    paddingValues: PaddingValues,
    viewState: BooksViewState,
    goToBookDetails: (bookName: String, bookId: String, coverImage: String) -> Unit,
    viewModel: BookListViewModel
) {
    val pagingBooks = rememberFlowWithLifecycle(viewState.pagedData).collectAsLazyPagingItems()

    SwipeRefresh(
        state = rememberSwipeRefreshState(
            isRefreshing = pagingBooks.loadState.refresh == LoadState.Loading && pagingBooks.itemCount == 0
        ),
        onRefresh = {
            viewModel.onTriggerEvent(BooksEvent.RefreshBooks)
            pagingBooks.refresh()
        },
        indicatorPadding = paddingValues,
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = trigger,
                scale = true
            )
        },
        content = {
            if (pagingBooks.itemCount > 0)
                LazyColumn(
                    modifier
                        .fillMaxSize()
                        .defaultScreenPadding(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(defaultPadding()),
                ) {
                    items(pagingBooks.itemCount) { index ->
                        pagingBooks[index]?.let {
                            BookRow(
                                dto = it,
                                onBookClick = goToBookDetails
                            )
                        }
                    }
                }
            else BookListLoadingView(modifier = modifier)
        }
    )
}


