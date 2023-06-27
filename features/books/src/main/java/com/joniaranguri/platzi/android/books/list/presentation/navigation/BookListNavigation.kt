package com.joniaranguri.platzi.android.books.list.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.joniaranguri.platzi.android.books.list.presentation.BookListScreen
import com.joniaranguri.platzi.android.ui.navigation.NavigationItem

fun NavGraphBuilder.bookListScreen(goToBookDetails: (String, String, String) -> Unit) {
    composable(route = NavigationItem.BookList.route) {
        BookListScreen(goToBookDetails)
    }
}