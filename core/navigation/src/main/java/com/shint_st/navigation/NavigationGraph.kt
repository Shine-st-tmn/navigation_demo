package com.shint_st.navigation

import androidx.navigation.NavGraphBuilder

interface NavigationGraph {
    val startDestination: String
    val navigationGraph: NavGraphBuilder.() -> Unit
}