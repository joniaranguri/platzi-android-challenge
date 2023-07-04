package com.joniaranguri.platzi.android.core.data.remote.model.base

import com.squareup.moshi.FromJson


class InconsistentTypeAdapter {
    /*
    * This Adapter is needed because the Api has an inconsistency in some fields:
    * Sometimes return a String and sometimes an Object.
    *
    */
    @FromJson
    fun fromJson(value: Any): InconsistentType {
        return when (value) {
            is String -> InconsistentType(value)
            else -> {
                val finalValue = value.toString()
                    .substringAfter('{')
                    .substringBeforeLast('}')
                    .replace("type=\\/.*\\/.*, value=".toRegex(), "")

                InconsistentType(finalValue)
            }
        }
    }
}
