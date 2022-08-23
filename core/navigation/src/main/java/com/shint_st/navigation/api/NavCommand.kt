package com.shint_st.navigation.api

sealed interface NavCommand {
    class Forward(
        val route: NavRoute,
        val routes: List<NavRoute> = listOf()
    ) : NavCommand

    class ProcessAction(val action: NavAction) : NavCommand

    class BackTo(
        val route: NavRoute,
        val inclusive: Boolean,
        val saveState: Boolean = false
    ) : NavCommand

    class SelectScope(val scope: NavScope) : NavCommand

    class Replace(val route: NavRoute) : NavCommand

    class NewStack(val route: NavRoute) : NavCommand

    object Back : NavCommand
}
