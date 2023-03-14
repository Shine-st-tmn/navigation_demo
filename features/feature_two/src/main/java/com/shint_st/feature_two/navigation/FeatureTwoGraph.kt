package com.shint_st.feature_two.navigation

import android.os.Parcelable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import com.shint_st.feature_two.FourFragment
import com.shint_st.feature_two.ThreeFragment
import com.shint_st.feature_two.TwoFragment
import com.shint_st.navigation.api.*
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

class FeatureTwoGraph @Inject constructor() : NavGraphComposer {
    override val startDestination: String = TwoFragmentGraphUnit.TAG
    override fun provideGraph(): NavGraphBuilder.() -> Unit = {
        also(TwoFragmentGraphUnit().provideGraphUnit())
        also(ThreeFragmentGraphUnit().provideGraphUnit())
        also(FourFragmentGraphUnit().provideGraphUnit())
    }
}

class TwoFragmentGraphUnit : NavGraphUnit {
    override fun provideGraphUnit(): NavGraphBuilder.() -> Unit = {
        fragment<TwoFragment>(TAG)
    }

    companion object {
        const val TAG = "TwoFragment_destination"
    }
}

class ThreeFragmentGraphUnit : NavGraphUnit {
    override fun provideGraphUnit(): NavGraphBuilder.() -> Unit = {
        fragment<ThreeFragment>(TAG)
    }

    @Parcelize
    data class Parameters(
        val text: String
    ) : Parcelable

    companion object {
        const val TAG = "ThreeFragment_nav_id"
    }
}

class FourFragmentGraphUnit : NavGraphUnit {
    override fun provideGraphUnit(): NavGraphBuilder.() -> Unit = {
        fragment<FourFragment>(TAG)
    }

    @Parcelize
    data class Parameters(
        val text: String
    ) : Parcelable

    companion object {
        const val TAG = "FourFragment_nav_id"
    }
}

class ThreeFragmentRoute(params: ThreeFragmentGraphUnit.Parameters) : NavRoute(
    ThreeFragmentGraphUnit.TAG,
    NavScope.HUB,
    params
)

class FourFragmentRoute(params: FourFragmentGraphUnit.Parameters) : NavRoute(
    FourFragmentGraphUnit.TAG,
    NavScope.HUB,
    params
)