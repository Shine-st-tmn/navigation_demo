package com.shint_st.navigation.utils

import android.os.Bundle
import androidx.core.view.forEach
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import com.google.android.material.navigation.NavigationBarView
import com.shint_st.navigation.api.NavScope
import java.lang.ref.WeakReference

typealias NavBarItemId = Int

internal class CustomNavigationUI(
    private val navGraphMap: Map<NavBarItemId, NavScope>
) {
    fun setupWithNavController(
        navigationBarView: NavigationBarView,
        navController: NavController
    ) {
        navigationBarView.setOnItemSelectedListener { item ->
            val graph = navGraphMap[item.itemId]?.tag ?: return@setOnItemSelectedListener false
            val builder = NavOptions.Builder().apply {
                setLaunchSingleTop(true)
                setRestoreState(true)
                setPopUpTo(
                    navController.graph.findStartDestination().id,
                    inclusive = false,
                    saveState = true
                )
            }

            return@setOnItemSelectedListener try {
                navController.navigate(graph, builder.build())
                navController.currentDestination?.matchDestination(graph) == true
            } catch (e: IllegalArgumentException) {
                false
            }
        }
        val weakReference = WeakReference(navigationBarView)
        navController.addOnDestinationChangedListener(
            object : NavController.OnDestinationChangedListener {
                override fun onDestinationChanged(
                    controller: NavController,
                    destination: NavDestination,
                    arguments: Bundle?
                ) {
                    val view = weakReference.get()
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

    private fun NavDestination.matchDestination(id: String): Boolean = hierarchy.any {
        it.route == id
    }
}

fun NavigationBarView.setupWithDSLNavController(
    navController: NavController,
    navGraphMap: Map<NavBarItemId, NavScope>
) = CustomNavigationUI(navGraphMap).setupWithNavController(
    this,
    navController
)
