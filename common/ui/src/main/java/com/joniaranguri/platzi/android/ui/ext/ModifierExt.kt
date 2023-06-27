package com.joniaranguri.platzi.android.ui.ext

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import com.joniaranguri.platzi.android.theme.Dimen.defaultPadding
import com.joniaranguri.platzi.android.theme.Dimen.littlePadding

fun Modifier.defaultScreenPadding(): Modifier = this
    .padding(horizontal = defaultPadding())
    .padding(bottom = defaultPadding())

fun Modifier.defaultContentPadding(): Modifier = this
    .padding(defaultPadding())

fun defaultPadding() = defaultPadding()

fun littlePadding() = littlePadding()