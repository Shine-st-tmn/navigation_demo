package com.shint_st.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import com.shint_st.login.LoginFragment
import com.shint_st.navigation.api.NavGraphComposer
import com.shint_st.navigation.api.NavGraphUnit
import com.shint_st.navigation.api.NavRoute
import com.shint_st.navigation.api.NavScope
import javax.inject.Inject

class LoginGraph @Inject constructor() : NavGraphComposer {
    override val startDestination: String = LoginGraphUnit.TAG
    override fun provideGraph(): NavGraphBuilder.() -> Unit = {
        also(LoginGraphUnit().provideGraphUnit())
    }
}

class LoginGraphUnit : NavGraphUnit {
    override fun provideGraphUnit(): NavGraphBuilder.() -> Unit = {
        fragment<LoginFragment>(TAG)
    }

    companion object {
        const val TAG = "LoginFragment_destination"
    }
}

object LoginRoute : NavRoute(LoginGraphUnit.TAG, NavScope.UNSPECIFIED)