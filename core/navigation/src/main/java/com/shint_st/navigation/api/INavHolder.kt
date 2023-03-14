package com.shint_st.navigation.api

import androidx.navigation.NavController

interface INavHolder {
    fun getNavigator(): INavigator?
    fun clearNavController()
    fun setNavController(controller: NavController)
}