package com.shint_st.navigation.api

sealed interface NavAction {
    class Forward(val route: NavRoute) : NavAction
    class ForwardStack(val routes: List<NavRoute>) : NavAction
    class BackTo(
        val route: NavRoute,
        val inclusive: Boolean,
        val saveState: Boolean = false
    ) : NavAction

    class Replace(val route: NavRoute) : NavAction
    class NewStack(val route: NavRoute) : NavAction
    object Back : NavAction
}
