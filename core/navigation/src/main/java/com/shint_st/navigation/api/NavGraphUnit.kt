package com.shint_st.navigation.api

import androidx.navigation.NavGraphBuilder

interface NavGraphUnit {
    fun provideGraphUnit(): NavGraphBuilder.() -> Unit
}
