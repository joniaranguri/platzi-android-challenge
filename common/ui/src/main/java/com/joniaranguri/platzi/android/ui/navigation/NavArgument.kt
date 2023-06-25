package com.joniaranguri.platzi.android.ui.navigation


sealed class NavArgument(val id: String, val default: String) {
    object BookIdDetails : NavArgument("BOOK_ID", "NONE")
    object BookNameDetails : NavArgument("BOOK_NAME", "NONE")
}