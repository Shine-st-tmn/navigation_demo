package com.shint_st.feature_two

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import com.shint_st.navigation.NavigationGraph

class FeatureTwoGraph : NavigationGraph {
    override val startDestination: String = TwoFragment.NAVIGATION_ID
    override val navigationGraph: NavGraphBuilder.() -> Unit = {
        fragment<TwoFragment>(TwoFragment.NAVIGATION_ID)

        fragment<ThreeFragment>("${ThreeFragment.NAVIGATION_ID}/{${ThreeFragment.ARGUMENTS}}") {
            argument(ThreeFragment.ARGUMENTS) {
                type = ThreeFragment.ParametersType()
                defaultValue = ThreeFragment.Parameters("ThreeFragment")
            }
        }
    }
}