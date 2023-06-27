package com.joniaranguri.platzi.android.challenge.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.joniaranguri.platzi.android.book_details.presentation.navigation.bookDetailsScreen
import com.joniaranguri.platzi.android.books.list.presentation.navigation.bookListScreen
import com.joniaranguri.platzi.android.ui.navigation.NavigationItem
import java.net.URLEncoder

@Composable
fun NavigationGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationItem.BookList.route
    ) {
        bookListScreen { bookName, bookId, coverImage ->
            val encodedImage = URLEncoder.encode(coverImage, "utf-8")
            val route = "${NavigationItem.BookDetails.route}/${bookName}/${bookId}/${encodedImage}"
            navController.navigate(route)
        }
        bookDetailsScreen {
            navController.popBackStack()
        }
    }
}
