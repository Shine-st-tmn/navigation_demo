package com.shint_st.navigation.utils

import androidx.navigation.NavController

fun <T> NavController.getNavArgs(key: String): T? =
    currentBackStackEntry?.savedStateHandle?.get<T>(key)
