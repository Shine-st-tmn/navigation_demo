package com.shint_st.navigation_demo.navigation

import android.os.Bundle
import androidx.core.view.forEach
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import com.google.android.material.navigation.NavigationBarView
import com.shint_st.navigation_demo.R
import java.lang.ref.WeakReference

internal object CustomNavigationUI {
    fun setupWithNavController(
        navigationBarView: NavigationBarView,
        navController: NavController
    ) {
        navigationBarView.setOnItemSelectedListener { item ->
            val graph = getGraphNameById(item.itemId) ?: return@setOnItemSelectedListener false
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
                        val graph = getGraphNameById(item.itemId) ?: return@forEach
                        if (destination.matchDestination(graph)) {
                            item.isChecked = true
                        }
                    }
                }
            })
    }

    private fun NavDestination.matchDestination(id: String): Boolean =
        hierarchy.any {
            it.route == id
        }

    private fun getGraphNameById(id: Int) = when (id) {
        R.id.feature_one_navigation -> TopGraph.FEATURE_ONE_NAVIGATION
        R.id.feature_two_navigation -> TopGraph.FEATURE_TWO_NAVIGATION
        else -> null
    }
}

fun NavigationBarView.setupWithDSLNavController(navController: NavController) {
    CustomNavigationUI.setupWithNavController(this, navController)
}
