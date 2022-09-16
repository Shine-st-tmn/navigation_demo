package com.shint_st.navigation

import com.shint_st.navigation.api.NavRouterHolder
import com.shint_st.navigation.internal.NavRouterHolderImpl

object NavRouterHolderFactory {
    private var navHolder: NavRouterHolder? = null

    fun getInstance(): NavRouterHolder {
        if (navHolder == null) navHolder = NavRouterHolderImpl()
        return navHolder!!
    }
}