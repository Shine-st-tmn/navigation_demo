package com.shint_st.navigation.utils

import android.os.Bundle
import androidx.navigation.NavType
import com.shint_st.navigation.api.NavParams
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

abstract class NavParamsSerializer<T : NavParams>(isNullable: Boolean) : NavType<T>(
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
