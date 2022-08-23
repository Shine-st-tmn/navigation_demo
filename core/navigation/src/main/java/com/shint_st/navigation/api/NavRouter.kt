package com.shint_st.navigation.api

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination

interface NavRouter {
    fun executeAction(command: NavCommand)
    fun findDestination(id: String): NavDestination?
    fun getCurrentDestination(): NavDestination?
    fun getCurrentBackStack(): NavBackStackEntry?
    fun getBackQueue(): ArrayDeque<NavBackStackEntry>
}