package com.shint_st.navigation.api

import androidx.navigation.NavGraphBuilder

interface NavGraphComposer {
    val startDestination: String
    fun provideGraph(): NavGraphBuilder.() -> Unit
}