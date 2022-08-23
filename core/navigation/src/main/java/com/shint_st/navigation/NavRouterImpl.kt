package com.shint_st.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import com.shint_st.navigation.api.NavAction
import com.shint_st.navigation.api.NavRoute
import com.shint_st.navigation.api.NavRouter
import javax.inject.Inject

class NavRouterImpl @Inject constructor(
    private val navController: NavController,
) : NavRouter {
    override fun executeAction(action: NavAction) {
        when (action) {
            NavAction.Back -> navController.popBackStack()
            is NavAction.BackTo -> navController.popBackStack(
                action.route.id,
                action.inclusive,
                action.saveState
            )
            is NavAction.Forward -> {
                changeScope(action.route.scope.tag)
                for (route in action.routes) {
                    navController.navigate(
                        getNavigationRoute(route),
                        NavOptions.Builder()
                            .setAnimation(route)
                            .build()
                    )
                }

                navController.navigate(
                    getNavigationRoute(action.route),
                    NavOptions.Builder()
                        .setAnimation(action.route)
                        .build()
                )
            }
            is NavAction.NewStack -> {
                changeScope(action.route.scope.tag)
                navController.navigate(
                    getNavigationRoute(action.route),
                    NavOptions.Builder()
                        .setExternal(action.route)
                        .setAnimation(action.route)
                        .build()
                )
            }
            is NavAction.Replace -> {
                changeScope(action.route.scope.tag)
                navController.navigate(
                    getNavigationRoute(action.route),
                    NavOptions.Builder()
                        .setReplace()
                        .setAnimation(action.route)
                        .build()
                )
            }
            is NavAction.SelectScope -> changeScope(action.scope.tag)
        }
    }

    override fun findDestination(id: String): NavDestination? = navController
        .findDestination(id)

    override fun getCurrentDestination(): NavDestination? = navController
        .currentDestination

    override fun getCurrentBackStack(): NavBackStackEntry? = navController
        .currentBackStackEntry

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
        return "${route.id}/$argument"
    }

    private fun NavOptions.Builder.setReplace(): NavOptions.Builder {
        val current = getCurrentDestination() ?: return this
        return this.setPopUpTo(current.route, true)
    }

    private fun NavOptions.Builder.setExternal(route: NavRoute): NavOptions.Builder =
        this.setPopUpTo(route.scope.tag, false)

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
            if (instance == null) {
                instance = NavRouterImpl(navController)
            }

            return instance!!
        }
    }
}