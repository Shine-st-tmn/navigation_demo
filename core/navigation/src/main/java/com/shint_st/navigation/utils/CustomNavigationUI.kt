package com.shint_st.navigation.utils

import android.os.Bundle
import androidx.core.view.forEach
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavOptions
import com.google.android.material.navigation.NavigationBarView
import com.shint_st.navigation.api.NavScope
import java.lang.ref.WeakReference

typealias NavBarItemId = Int

internal class CustomNavigationUI(
    private val navGraphMap: Map<NavBarItemId, NavScope>
) {
    fun setupWithNavController(
        navigationBarView: WeakReference<NavigationBarView>,
        navController: NavController,
    ) {
        navigationBarView.get()?.onSelectListener(navController)
        navController.addOnDestinationChangedListener(
            object : NavController.OnDestinationChangedListener {
                override fun onDestinationChanged(
                    controller: NavController,
                    destination: NavDestination,
                    arguments: Bundle?
                ) {
                    val view = navigationBarView.get()
                    if (view == null) {
                        navController.removeOnDestinationChangedListener(this)
                        return
                    }
                    view.menu.forEach { item ->
                        val graph = navGraphMap[item.itemId]?.tag ?: return@forEach
                        if (destination.matchDestination(graph)) {
                            item.isChecked = true
                        }
                    }
                }
            })
    }

    private fun NavigationBarView.onSelectListener(
        navController: NavController,
    ) = setOnItemSelectedListener { item ->
        val graph = navGraphMap[item.itemId]?.tag ?: return@setOnItemSelectedListener false
        val lastDestination = navController.findLastDestination(graph)?.route

        return@setOnItemSelectedListener try {
            if (lastDestination != null) {
                navController.popBackStack(
                    route = lastDestination,
                    inclusive = false,
                    saveState = true
                )
            } else {
                navController.navigate(graph, NavOptions.Builder().build())
            }

            navController.currentDestination?.matchDestination(graph) == true
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    private fun NavDestination.matchDestination(id: String): Boolean = hierarchy.any {
        it.route == id
    }

    private fun NavController.findLastDestination(graphStart: String): NavDestination? =
        backQueue.lastOrNull { it.destination.parent?.route == graphStart }?.destination
}

fun NavigationBarView.setupWithDSLNavController(
    navController: NavController,
    navGraphMap: Map<NavBarItemId, NavScope>
) = CustomNavigationUI(navGraphMap).setupWithNavController(
    WeakReference(this),
    navController,
)
