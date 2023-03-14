package com.shint_st.navigation.api

sealed interface NavCommand {
    class Forward(
        val route: NavRoute,
        val routes: List<NavRoute> = listOf()
    ) : NavCommand

    class SelectScope(val scope: NavScope) : NavCommand

    class BackTo(
        val route: NavRoute,
        val inclusive: Boolean,
        val saveState: Boolean = false
    ) : NavCommand

    class Replace(
        val route: NavRoute,
        val routes: List<NavRoute> = listOf()
    ) : NavCommand

    class NewStack(
        val route: NavRoute,
        val routes: List<NavRoute> = listOf()
    ) : NavCommand

    object Back : NavCommand
}
