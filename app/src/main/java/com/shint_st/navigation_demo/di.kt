package com.shint_st.navigation_demo

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.shint_st.navigation.api.INavRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object AnalyticsModule