package com.shint_st.navigation.safe_args

import android.os.Bundle
import android.os.Parcelable

interface BaseArgs : Parcelable {
    fun buildBundle(): Bundle

    companion object {
        const val REQUIRED_PARAM = "id"
    }
}