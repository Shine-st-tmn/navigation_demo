package com.shint_st.feature_one.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import com.shint_st.feature_one.OneFragment
import com.shint_st.navigation.NavigationGraph
import javax.inject.Inject

class FeatureOneGraph @Inject constructor(): NavigationGraph {
    override val startDestination: String = OneFragment.NAVIGATION_ID
    override val navigationGraph: NavGraphBuilder.() -> Unit = {
        fragment<OneFragment>(OneFragment.NAVIGATION_ID)
    }
}