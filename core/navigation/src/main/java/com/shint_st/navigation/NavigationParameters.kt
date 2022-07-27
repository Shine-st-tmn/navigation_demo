package com.shint_st.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


abstract class NavigationParameters<T : Parcelable>(isNullable: Boolean) : NavType<T>(
    isNullableAllowed = isNullable
) {
    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key, value)
    }

    override fun get(bundle: Bundle, key: String): T {
        return bundle.getParcelable(key)!!
    }

    protected inline fun <reified A : T> parseValueDefault(value: String): A {
        return Json.decodeFromString(value)
    }
}