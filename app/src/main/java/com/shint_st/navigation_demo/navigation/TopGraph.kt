package com.shint_st.navigation_demo.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.shint_st.feature_one.navigation.FeatureOneGraph
import com.shint_st.feature_two.navigation.FeatureTwoGraph
import com.shint_st.login.navigation.LoginGraph
import com.shint_st.navigation.api.NavGraphComposer
import com.shint_st.navigation.api.NavScope

class TopGraph(isLogin: Boolean) : NavGraphComposer {
    private val featureOneGraph: FeatureOneGraph = FeatureOneGraph()
    private val featureTwoGraph: FeatureTwoGraph = FeatureTwoGraph()
    private val loginGraph: LoginGraph = LoginGraph()

    override val startDestination: String =
        if (isLogin) NavScope.UNSPECIFIED.tag else NavScope.HOME.tag

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