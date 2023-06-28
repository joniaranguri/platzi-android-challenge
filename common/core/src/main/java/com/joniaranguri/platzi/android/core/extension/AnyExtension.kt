package com.joniaranguri.platzi.android.core.extension

inline fun <reified T : Any> Any.cast(): T {
    return this as T
}