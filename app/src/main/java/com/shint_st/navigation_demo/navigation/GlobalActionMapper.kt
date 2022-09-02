package com.shint_st.navigation_demo.navigation

import com.shint_st.feature_one.navigation.OneFragmentRoute
import com.shint_st.navigation.api.NavAction
import com.shint_st.navigation.api.NavActionsMapper
import com.shint_st.navigation.api.NavCommand
import com.shint_st.navigation.api.NavRouter
import com.shint_st.navigation.sharedactions.GoToMainScreen
import javax.inject.Inject

class GlobalActionMapper @Inject constructor(private val router: NavRouter) : NavActionsMapper {
    override fun navigate(action: NavAction): Boolean {
        val command = when (action) {
            is GoToMainScreen -> NavCommand.NewStack(OneFragmentRoute())
            else -> null
        }

        command?.let { router.executeCommand(it) }
        return command != null
    }
}