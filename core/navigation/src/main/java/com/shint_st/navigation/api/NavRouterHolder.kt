package com.shint_st.navigation.api

import androidx.navigation.NavController

interface NavRouterHolder {
    fun getNavRouter(): NavRouter
    fun clearNavController()
    fun setNavController(controller: NavController)
}