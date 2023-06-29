package com.joniaranguri.platzi.android.book_details.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.joniaranguri.platzi.android.ui.ext.defaultContentPadding
import com.joniaranguri.platzi.android.ui.ext.defaultPadding
import com.joniaranguri.platzi.android.ui.ext.doublePadding
import com.joniaranguri.platzi.android.ui.widgets.ShimmerCard
import com.joniaranguri.platzi.android.ui.widgets.ShimmerText


@Composable
fun LoadingBookDetailView(modifier: Modifier) {
    val bookSize = LocalConfiguration.current.screenWidthDp.dp * .7F
    Column(
        modifier = modifier
            .fillMaxSize()
            .defaultContentPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        ShimmerCard(
            modifier = Modifier
                .fillMaxWidth(.5F)
                .defaultMinSize(bookSize, bookSize)
                .padding(vertical = defaultPadding()),
            height = 80.dp
        )
        ShimmerText(modifier = Modifier.padding(horizontal = doublePadding()), lines = 3)
        ShimmerText(modifier = Modifier.padding(horizontal = doublePadding()), lines = 7)

    }
}
