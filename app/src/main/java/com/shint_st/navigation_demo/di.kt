package com.shint_st.navigation_demo

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.shint_st.navigation.NavRouterImpl
import com.shint_st.navigation.api.NavRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object AnalyticsModule {
    @Provides
    fun provideAnalyticsService(
        @ActivityContext appContext: Context
    ): NavController {
        val activity = appContext as MainActivity

        return activity.findNavController(R.id.nav_host_fragment_activity_main)
    }

    @Provides
    fun provideNavRouter(navController: NavController): NavRouter =
        NavRouterImpl.getInstance(navController)
}