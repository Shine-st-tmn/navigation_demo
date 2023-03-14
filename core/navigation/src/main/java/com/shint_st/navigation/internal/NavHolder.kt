package com.shint_st.navigation.internal

import androidx.navigation.NavController
import com.shint_st.navigation.api.INavHolder
import com.shint_st.navigation.api.INavigator
import dagger.hilt.components.SingletonComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import javax.inject.Inject
import javax.inject.Singleton

@BoundTo(supertype = INavHolder::class, component = SingletonComponent::class)
@Singleton
internal class NavHolder @Inject constructor() : INavHolder {
    private var navigator: INavigator? = null

    override fun getNavigator(): INavigator? = navigator

    override fun clearNavController() {
        navigator = null
    }

    override fun setNavController(controller: NavController) {
        navigator = Navigator(controller)
    }
}