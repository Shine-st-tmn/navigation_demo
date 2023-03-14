package com.shint_st.navigation.animation

interface NavAnimation {
    val enterAnim: Int
    val exitAnim: Int
    val popEnterAnim: Int
    val popExitAnim: Int

    companion object {
        const val NO_ANIMATION_VALUE = -1
    }
}