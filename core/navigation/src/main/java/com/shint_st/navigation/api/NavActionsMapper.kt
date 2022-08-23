package com.shint_st.navigation.api

fun interface NavActionsMapper {
    fun mapNavAction(action: NavAction, router: NavRouter)
}