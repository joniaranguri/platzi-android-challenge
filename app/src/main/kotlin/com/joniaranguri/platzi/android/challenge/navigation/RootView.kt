package com.joniaranguri.platzi.android.challenge.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joniaranguri.platzi.android.books.list.presentation.BooksScreen
import com.joniaranguri.platzi.android.theme.PlatziChallengeTheme

@Composable
fun RootView(){
    PlatziChallengeTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            BooksScreen(name = "Platzi!")
        }
    }
}