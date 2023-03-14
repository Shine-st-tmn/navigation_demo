package com.shint_st.navigation.api

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination

interface INavigator {
    fun executeCommand(command: NavCommand)
    fun findDestination(id: String): NavDestination?
    fun getCurrentDestination(): NavDestination?
    fun getCurrentBackStack(): NavBackStackEntry?
    fun getBackQueue(): ArrayDeque<NavBackStackEntry>
}