package com.shint_st.navigation.api

import com.shint_st.navigation.animation.NavAnimation

abstract class NavRoute(
    val id: String,
    val scope: NavScope,
    val params: NavParams? = null,
    val animation: NavAnimation? = null
) {
    open fun getParamsString(): String? = null
}