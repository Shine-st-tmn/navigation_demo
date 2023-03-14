package com.shint_st.navigation.animation

import com.shint_st.navigation.R

class DefaultNavAnimation : NavAnimation {
    override val enterAnim: Int = R.anim.slide_to_left
    override val exitAnim: Int = R.anim.slide_to_left
    override val popEnterAnim: Int = R.anim.slide_to_right
    override val popExitAnim: Int = R.anim.slide_to_right
}