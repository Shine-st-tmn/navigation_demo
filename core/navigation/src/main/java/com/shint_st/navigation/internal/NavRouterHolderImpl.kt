package com.shint_st.navigation.internal

import androidx.navigation.NavController
import com.shint_st.navigation.api.NavRouter
import com.shint_st.navigation.api.NavRouterHolder

internal class NavRouterHolderImpl : NavRouterHolder {
    private var router: NavRouter? = null

    override fun getNavRouter(): NavRouter = router!!

    override fun clearNavController() {
        router = null
    }

    override fun setNavController(controller: NavController) {
        router = NavRouterImpl(controller)
    }
}