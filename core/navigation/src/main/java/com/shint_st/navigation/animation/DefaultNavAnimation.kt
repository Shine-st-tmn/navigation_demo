package com.shint_st.navigation.animation

import com.shint_st.navigation.R

class DefaultNavAnimation : NavAnimation {
    override val enterAnim: Int = R.anim.slide_to_left
    override val exitAnim: Int = R.anim.slide_to_left
    override val popEnterAnim: Int = -1
    override val popExitAnim: Int = -1
}