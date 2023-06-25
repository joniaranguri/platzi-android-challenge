package com.joniaranguri.platzi.android.ui.ext

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import com.joniaranguri.platzi.android.theme.Dimen.defaultPadding

fun Modifier.defaultScreenPadding(): Modifier = this
    .padding(horizontal = defaultPadding())
    .padding(bottom = defaultPadding())
