package com.shint_st.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import com.shint_st.navigation.api.NavCommand
import com.shint_st.navigation.api.NavRoute
import com.shint_st.navigation.api.NavRouter
import javax.inject.Inject

class NavRouterImpl @Inject constructor(
    private val navController: NavController
) : NavRouter {
    override fun executeCommand(command: NavCommand) {
        when (command) {
            NavCommand.Back -> navController.popBackStack()
            is NavCommand.BackTo -> navController.popBackStack(
                command.route.id,
                command.inclusive,
                command.saveState
            )
            is NavCommand.Forward -> {
                changeScope(command.route.scope.tag)
                for (route in command.routes) {
                    navController.navigate(
                        getNavigationRoute(route),
                        NavOptions.Builder()
                            .setAnimation(route)
                            .build()
                    )
                }

                navController.navigate(
                    getNavigationRoute(command.route),
                    NavOptions.Builder()
                        .setAnimation(command.route)
                        .build()
                )
            }
            is NavCommand.NewStack -> {
                changeScope(command.route.scope.tag)
                navController.navigate(
                    getNavigationRoute(command.route),
                    NavOptions.Builder()
                        .setExternal(command.route)
                        .setAnimation(command.route)
                        .build()
                )
            }
            is NavCommand.Replace -> {
                changeScope(command.route.scope.tag)
                navController.navigate(
                    getNavigationRoute(command.route),
                    NavOptions.Builder()
                        .setReplace()
                        .setAnimation(command.route)
                        .build()
                )
            }
            is NavCommand.SelectScope -> changeScope(command.scope.tag)
        }
    }

    override fun findDestination(id: String): NavDestination? = navController
        .findDestination(id)

    override fun getCurrentDestination(): NavDestination? = navController
        .currentDestination

    override fun getCurrentBackStack(): NavBackStackEntry? = navController
        .currentBackStackEntry

    override fun getBackQueue() = navController.backQueue

    private fun changeScope(graphTag: String) {
        if (navController.currentDestination?.parent?.route == graphTag) return
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

    private fun getNavigationRoute(route: NavRoute): String {
        val argument = route.getParamsString()
        return if (argument == null) route.id else "${route.id}/$argument"
    }

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

    companion object {
        var instance: NavRouter? = null

        @Synchronized
        fun getInstance(navController: NavController): NavRouter {
            if (instance == null) instance = NavRouterImpl(navController)
            return instance!!
        }
    }
}