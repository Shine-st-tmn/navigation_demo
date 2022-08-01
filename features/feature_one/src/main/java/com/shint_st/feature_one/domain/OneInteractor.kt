package com.shint_st.feature_one.domain

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface OneInteractor {
    suspend fun makeSomething()
}