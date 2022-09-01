package com.shint_st.navigation.api

interface NavActionsMapper {
    fun navigate(action: NavAction): Boolean
}