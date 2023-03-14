package com.shint_st.navigation.internal

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import com.shint_st.navigation.api.*
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import javax.inject.Inject

internal class Navigator(
    private val navController: NavController,
) : INavigator {
    override fun executeCommand(command: NavCommand) {
        when (command) {
            NavCommand.Back -> navController.popBackStack()
            is NavCommand.BackTo -> navController.popBackStack(
                command.route.id,
                command.inclusive,
                command.saveState
            )
            is NavCommand.Forward -> {
                command.routes.setInBackstack()
                navController.navigate(
                    getNavigationRoute(command.route),
                    NavOptions.Builder()
                        .setAnimation(command.route)
                        .build()
                )
                navController.saveDataInSavedState(command.route)
            }
            is NavCommand.NewStack -> {
                navController.navigate(
                    getNavigationRoute(command.route),
                    NavOptions.Builder()
                        .setExternal(command.route)
                        .setAnimation(command.route)
                        .build()
                )
                navController.saveDataInSavedState(command.route)
                command.routes.setInBackstack()
            }
            is NavCommand.Replace -> {
                navController.navigate(
                    getNavigationRoute(command.route),
                    NavOptions.Builder()
                        .setReplace()
                        .setAnimation(command.route)
                        .build()
                )
                navController.saveDataInSavedState(command.route)
                command.routes.setInBackstack()
            }
            is NavCommand.SelectScope -> changeScope(command.scope.tag)
        }
    }

    override fun findDestination(id: String): NavDestination? = navController.findDestination(id)

    override fun getCurrentDestination(): NavDestination? = navController.currentDestination

    override fun getCurrentBackStack(): NavBackStackEntry? = navController.currentBackStackEntry

    override fun getBackQueue(): ArrayDeque<NavBackStackEntry> = navController.backQueue

    private fun List<NavRoute>.setInBackstack() {
        for (route in this) {
            navController.navigate(
                getNavigationRoute(route),
                NavOptions.Builder()
                    .setAnimation(route)
                    .build()
            )
            navController.saveDataInSavedState(route)
        }
    }

    private fun NavController.saveDataInSavedState(navRoute: NavRoute) {
        if (navRoute.params != null) {
            getBackStackEntry(navRoute.id).savedStateHandle.set(navRoute.id, navRoute.params)
        }
    }

    private fun changeScope(graphTag: String) {
        if (navController.currentDestination?.parent?.route == graphTag
            || graphTag == NavScope.UNSPECIFIED.tag
        ) return
        val builder = NavOptions.Builder().apply {
            setLaunchSingleTop(true)
            setRestoreState(true)
            setPopUpTo(
                navController.graph.findStartDestination().id,
                inclusive = false,
                saveState = true
            )
        }

        navController.navigate(graphTag, builder.build())
    }

    private fun getNavigationRoute(route: NavRoute) = route.id

    private fun NavOptions.Builder.setReplace(): NavOptions.Builder {
        val current = getCurrentDestination() ?: return this
        return this.setPopUpTo(current.route, true)
    }

    private fun NavOptions.Builder.setExternal(route: NavRoute): NavOptions.Builder = this.apply {
        setPopUpTo(route.scope.tag, true)
        setLaunchSingleTop(true)
    }

    private fun NavOptions.Builder.setAnimation(route: NavRoute): NavOptions.Builder {
        val animation = route.animation ?: return this

        return this.setEnterAnim(animation.enterAnim)
            .setExitAnim(animation.exitAnim)
            .setPopEnterAnim(animation.popEnterAnim)
            .setPopExitAnim(animation.popExitAnim)
    }
}