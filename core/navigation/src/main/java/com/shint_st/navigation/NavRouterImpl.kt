package com.shint_st.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import com.shint_st.navigation.api.NavAction
import com.shint_st.navigation.api.NavRoute
import com.shint_st.navigation.api.NavRouter
import dagger.hilt.android.components.ActivityComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import javax.inject.Inject

@BoundTo(supertype = NavRouter::class, component = ActivityComponent::class)
internal class NavRouterImpl @Inject constructor(
    private val navController: NavController
) : NavRouter {

    override fun executeAction(action: NavAction) {
        when (action) {
            NavAction.Back -> navController.popBackStack()
            is NavAction.BackTo -> navController.popBackStack(
                action.route.id,
                action.inclusive,
                action.saveState
            )
            is NavAction.Forward -> navController.navigate(
                getNavigationRoute(action.route),
                NavOptions.Builder()
                    .setAnimation(action.route)
                    .build()
            )
            is NavAction.NewStack -> navController.navigate(
                getNavigationRoute(action.route),
                NavOptions.Builder()
                    .setExternal(action.route)
                    .setAnimation(action.route)
                    .build()
            )
            is NavAction.Replace -> navController.navigate(
                getNavigationRoute(action.route),
                NavOptions.Builder()
                    .setReplace()
                    .setAnimation(action.route)
                    .build()
            )
        }
    }

    override fun findDestination(id: String): NavDestination? = navController
        .findDestination(id)

    override fun getCurrentDestination(): NavDestination? = navController
        .currentDestination

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

}