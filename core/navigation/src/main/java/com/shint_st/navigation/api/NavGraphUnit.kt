package com.shint_st.navigation.api

import androidx.navigation.NavGraphBuilder

interface NavGraphUnit {
    fun provideGraphUnit(): NavGraphBuilder.() -> Unit
    fun makeGraphKey(navTag: String, navArgs: String? = null): String {
        if (navArgs != null) return "$navTag/{$navArgs}"
        return navTag
    }
}
