package com.joniaranguri.platzi.android.book_details.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.joniaranguri.platzi.android.book_details.presentation.BookDetailsScreen
import com.joniaranguri.platzi.android.ui.navigation.NavArgument
import com.joniaranguri.platzi.android.ui.navigation.NavigationItem

fun NavGraphBuilder.bookDetailsScreen(goBack: () -> Unit) {
    composable(route = NavigationItem.BookDetails.getFullRoute()) {
        BookDetailsScreen(
            bookId = it.arguments?.getString(NavArgument.BookIdDetails.id)
                ?: NavArgument.BookIdDetails.default,
            title = it.arguments?.getString(NavArgument.BookNameDetails.id)
                ?: NavArgument.BookNameDetails.default,
            coverImageUrl = it.arguments?.getString(NavArgument.BookCoverImageDetails.id)
                ?: NavArgument.BookCoverImageDetails.default,
            goBack
        )
    }
}