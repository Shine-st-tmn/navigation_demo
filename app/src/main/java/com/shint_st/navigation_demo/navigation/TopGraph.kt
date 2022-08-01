package com.shint_st.navigation_demo.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.shint_st.feature_one.navigation.FeatureOneGraph
import com.shint_st.feature_two.FeatureTwoGraph
import com.shint_st.navigation.NavigationGraph
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@BoundTo(supertype = NavigationGraph::class, component = SingletonComponent::class)
class TopGraph @Inject constructor(
    private val featureOneGraph: FeatureOneGraph,
    private val featureTwoGraph: FeatureTwoGraph,
) : NavigationGraph {
    override val startDestination: String = FEATURE_ONE_NAVIGATION

    override val navigationGraph: NavGraphBuilder.() -> Unit = {
        navigation(featureOneGraph.startDestination, FEATURE_ONE_NAVIGATION) {
            also(featureOneGraph.navigationGraph)
        }

        navigation(featureTwoGraph.startDestination, FEATURE_TWO_NAVIGATION) {
            also(featureTwoGraph.navigationGraph)
        }
    }

    companion object {
        const val FEATURE_ONE_NAVIGATION = "feature_one_navigation"
        const val FEATURE_TWO_NAVIGATION = "feature_two_navigation"
    }
}