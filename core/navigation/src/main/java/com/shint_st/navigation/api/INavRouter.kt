package com.shint_st.navigation.api

interface INavRouter {
    fun navigate(action: NavAction): Boolean
}
