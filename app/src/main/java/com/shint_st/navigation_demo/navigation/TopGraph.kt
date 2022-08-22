package com.shint_st.navigation_demo.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.shint_st.feature_one.navigation.FeatureOneGraph
import com.shint_st.feature_two.FeatureTwoGraph
import com.shint_st.navigation.api.NavGraphComposer
import com.shint_st.navigation.api.NavScope
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@BoundTo(supertype = NavGraphComposer::class, component = SingletonComponent::class)
class TopGraph @Inject constructor(
    private val featureOneGraph: FeatureOneGraph,
    private val featureTwoGraph: FeatureTwoGraph,
) : NavGraphComposer {
    override val startDestination: String = NavScope.HOME.tag

    override fun provideGraph(): NavGraphBuilder.() -> Unit = {
        navigation(featureOneGraph.startDestination, NavScope.HOME.tag) {
            also(featureOneGraph.provideGraph())
        }

        navigation(featureTwoGraph.startDestination, NavScope.HUB.tag) {
            also(featureTwoGraph.provideGraph())
        }
    }
}