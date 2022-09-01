package com.shint_st.feature_two.navigation

import com.shint_st.navigation.api.NavAction
import com.shint_st.navigation.api.NavActionsMapper
import com.shint_st.navigation.api.NavCommand
import com.shint_st.navigation.api.NavRouter
import javax.inject.Inject

class FeatureTwoNavActionsMapper @Inject constructor(
    private val router: NavRouter
) : NavActionsMapper {

    override fun navigate(action: NavAction): Boolean {
        val command = when (action) {
            is FourFragmentAction -> NavCommand.NewStack(
                FourFragmentRoute(
                    FourFragmentGraphUnit.Parameters(
                        "test text"
                    )
                )
            )
            is FourFragmentWithTailAction -> NavCommand.Forward(
                FourFragmentRoute(
                    FourFragmentGraphUnit.Parameters(
                        "test text"
                    )
                ),
                listOf(
                    ThreeFragmentRoute(
                        ThreeFragmentGraphUnit.Parameters("42 is antwort")
                    ),
                )
            )
            else -> null
        }
        command?.let { router.executeCommand(it) }

        return command != null
    }
}