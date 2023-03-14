package com.shint_st.feature_two.navigation

import com.shint_st.navigation.api.*
import javax.inject.Inject

class FeatureTwoNavActionsRouter @Inject constructor(
    private val navHolder: INavHolder
) : INavRouter {

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
        } ?: return false

        navHolder.getNavigator()?.executeCommand(command)

        return true
    }
}