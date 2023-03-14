package com.shint_st.navigation_demo.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.shint_st.feature_one.navigation.FeatureOneGraph
import com.shint_st.feature_two.navigation.FeatureTwoGraph
import com.shint_st.login.navigation.LoginGraph
import com.shint_st.navigation.api.NavGraphComposer
import com.shint_st.navigation.api.NavScope
import javax.inject.Inject

class TopGraph @Inject constructor(
    private val featureOneGraph: FeatureOneGraph,
    private val featureTwoGraph: FeatureTwoGraph,
    private val loginGraph: LoginGraph
) : NavGraphComposer {

    override val startDestination: String = NavScope.HOME.tag

    override fun provideGraph(): NavGraphBuilder.() -> Unit = {
        navigation(loginGraph.startDestination, NavScope.UNSPECIFIED.tag) {
            also(loginGraph.provideGraph())
        }

        navigation(featureOneGraph.startDestination, NavScope.HOME.tag) {
            also(featureOneGraph.provideGraph())
        }

        navigation(featureTwoGraph.startDestination, NavScope.HUB.tag) {
            also(featureTwoGraph.provideGraph())
        }
    }
}