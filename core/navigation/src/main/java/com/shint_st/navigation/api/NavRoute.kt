package com.shint_st.navigation.api

import android.os.Parcelable
import com.shint_st.navigation.R
import com.shint_st.navigation.animation.NavAnimation

abstract class NavRoute(
    val id: String,
    val scope: NavScope,
    val params: Parcelable? = null,
    val animation: NavAnimation = object : NavAnimation {
        override val enterAnim = R.anim.fade_up
        override val exitAnim = NavAnimation.NO_ANIMATION_VALUE
        override val popEnterAnim = NavAnimation.NO_ANIMATION_VALUE
        override val popExitAnim = NavAnimation.NO_ANIMATION_VALUE
    }
)
