package com.shint_st.feature_one.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import com.shint_st.feature_one.OneFragment
import com.shint_st.navigation.api.NavGraphComposer
import com.shint_st.navigation.api.NavGraphUnit
import com.shint_st.navigation.api.NavRoute
import com.shint_st.navigation.api.NavScope
import javax.inject.Inject

class FeatureOneGraph @Inject constructor() : NavGraphComposer {
    override val startDestination: String = OneFragmentNavGraphUnit.TAG
    override fun provideGraph(): NavGraphBuilder.() -> Unit = {
        also(OneFragmentNavGraphUnit().provideGraphUnit())
    }
}

class OneFragmentNavGraphUnit : NavGraphUnit {
    override fun provideGraphUnit(): NavGraphBuilder.() -> Unit = {
        fragment<OneFragment>(TAG)
    }

    companion object {
        const val TAG = "OneFragment_destination"
    }
}

class OneFragmentRoute : NavRoute(OneFragmentNavGraphUnit.TAG, scope = NavScope.HOME)
