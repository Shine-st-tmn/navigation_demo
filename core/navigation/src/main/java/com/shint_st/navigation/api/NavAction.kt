package com.shint_st.navigation.api

sealed interface NavAction {
    class Forward(
        val route: NavRoute,
        val routes: List<NavRoute> = listOf()
    ) : NavAction

    class BackTo(
        val route: NavRoute,
        val inclusive: Boolean,
        val saveState: Boolean = false
    ) : NavAction

    class SelectScope(val scope: NavScope) : NavAction
    class Replace(val route: NavRoute) : NavAction
    class NewStack(val route: NavRoute) : NavAction
    object Back : NavAction
}
