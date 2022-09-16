package com.shint_st.navigation.utils

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.google.android.material.navigation.NavigationBarView
import com.shint_st.navigation.api.NavScope
import java.lang.ref.WeakReference

private object HideNavigationBar {
    fun hideUnspecifiedDestinations(
        navigationBarView: NavigationBarView,
        navController: NavController
    ) {
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
                    view.isVisible = !destination.isNotUnspecified()
                }
            })
    }

    private fun NavDestination.isNotUnspecified() = hierarchy.any {
        it.route == NavScope.UNSPECIFIED.tag
    }
}

fun NavigationBarView.hideUnspecifiedDestinations(
    navController: NavController,
) = HideNavigationBar.hideUnspecifiedDestinations(
    this,
    navController
)
