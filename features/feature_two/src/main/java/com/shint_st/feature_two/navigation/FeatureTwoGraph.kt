package com.shint_st.feature_two.navigation

import android.net.Uri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import com.shint_st.feature_two.FourFragment
import com.shint_st.feature_two.ThreeFragment
import com.shint_st.feature_two.TwoFragment
import com.shint_st.navigation.api.*
import com.shint_st.navigation.utils.NavParamsSerializer
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
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
        fragment<ThreeFragment>(makeGraphKey(TAG, ARGUMENTS)) {
            argument(ARGUMENTS) {
                type = ParametersType()
                nullable = true
            }
        }
    }

    @Serializable
    @Parcelize
    data class Parameters(
        val text: String
    ) : NavParams()

    class ParametersType : NavParamsSerializer<Parameters>(true) {
        override fun parseValue(value: String): Parameters = parseValueDefault(value)
    }

    companion object {
        const val TAG = "ThreeFragment_nav_id"
        const val ARGUMENTS = "ThreeFragment_arguments"
    }
}

class FourFragmentGraphUnit : NavGraphUnit {
    override fun provideGraphUnit(): NavGraphBuilder.() -> Unit = {
        fragment<FourFragment>(makeGraphKey(TAG, ARGUMENTS)) {
            argument(ARGUMENTS) {
                type = ParametersType()
                defaultValue = Parameters("FourFragment")
                nullable = true
            }
        }
    }

    @Serializable
    @Parcelize
    data class Parameters(
        val text: String
    ) : NavParams()

    class ParametersType : NavParamsSerializer<Parameters>(true) {
        override fun parseValue(value: String): Parameters = parseValueDefault(value)
    }

    companion object {
        const val TAG = "FourFragment_nav_id"
        const val ARGUMENTS = "FourFragment_arguments"
    }
}

class ThreeFragmentRoute(params: ThreeFragmentGraphUnit.Parameters?) : NavRoute(
    ThreeFragmentGraphUnit.TAG,
    NavScope.HUB,
    params
) {
    override fun getParamsString(): String? = Uri.encode(
        Json.encodeToString(
            params as? ThreeFragmentGraphUnit.Parameters
        )
    )
}

class FourFragmentRoute(params: FourFragmentGraphUnit.Parameters?) : NavRoute(
    FourFragmentGraphUnit.TAG,
    NavScope.HUB,
    params
) {
    override fun getParamsString(): String? = Uri.encode(
        Json.encodeToString(
            params as? FourFragmentGraphUnit.Parameters
        )
    )
}