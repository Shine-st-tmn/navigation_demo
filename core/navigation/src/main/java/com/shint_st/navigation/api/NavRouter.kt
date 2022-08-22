package com.shint_st.navigation.api

import androidx.navigation.NavDestination
import com.shint_st.navigation.api.NavAction
import kotlinx.coroutines.flow.SharedFlow

interface NavRouter {
    val currentScopeFlow: SharedFlow<NavScope>
    fun executeAction(action: NavAction)
    fun findDestination(id: String): NavDestination?
    fun getCurrentDestination(): NavDestination?
}