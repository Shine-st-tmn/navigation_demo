package com.shint_st.navigation_demo.navigation

import com.shint_st.feature_one.navigation.OneFragmentRoute
import com.shint_st.navigation.api.INavHolder
import com.shint_st.navigation.api.INavRouter
import com.shint_st.navigation.api.NavAction
import com.shint_st.navigation.api.NavCommand
import com.shint_st.navigation.utils.GoToMainScreen
import javax.inject.Inject

class GlobalActionRouter @Inject constructor(
    private val navHolder: INavHolder
) : INavRouter {
    override fun navigate(action: NavAction): Boolean {
        val command = when (action) {
            is GoToMainScreen -> NavCommand.NewStack(OneFragmentRoute())
            else -> null
        } ?: return false

        navHolder.getNavigator()?.executeCommand(command)

        return true
    }
}