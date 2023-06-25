package com.joniaranguri.platzi.android.ui.navigation

sealed class NavigationItem(
    val route: String,
    val title: String,
    val getFullRoute: () -> String = { route }
) {
    object BookList : NavigationItem(ScreenRoutes.BOOK_LIST, "Recommended books")
    object BookDetails : NavigationItem(
        ScreenRoutes.BOOK_DETAILS,
        "Detail",
        { "${ScreenRoutes.BOOK_DETAILS}/{${NavArgument.BookNameDetails.id}}/{${NavArgument.BookIdDetails.id}}" })
}

private object ScreenRoutes {
    const val BOOK_LIST = "BOOK_LIST"
    const val BOOK_DETAILS = "BOOK_DETAILS"
}