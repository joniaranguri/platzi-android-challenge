package com.joniaranguri.platzi.android.books.list.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joniaranguri.platzi.android.ui.ext.defaultContentPadding
import com.joniaranguri.platzi.android.ui.ext.defaultPadding
import com.joniaranguri.platzi.android.ui.ext.doublePadding
import com.joniaranguri.platzi.android.ui.widgets.ShimmerCard
import com.joniaranguri.platzi.android.ui.widgets.ShimmerText

@Composable
fun BookListLoadingView(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .defaultContentPadding()
    ) {
        repeat(10) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShimmerText(
                    modifier = Modifier
                        .fillMaxWidth(.7f)
                        .padding(end = doublePadding()),
                    lines = 3
                )
                ShimmerCard(height = 150.dp, modifier = Modifier.padding(defaultPadding()))
            }
        }
    }
}
