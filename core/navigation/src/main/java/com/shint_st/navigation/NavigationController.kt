package com.shint_st.navigation

interface NavigationController {
    fun selectTab(tab: Tab, onSelected: (() -> Unit)? = null)
}