package com.shint_st.feature_one.domain

import android.util.Log
import com.shint_st.feature_one.data.NetworkClient
import com.shint_st.feature_one.di.AuthNetworkClientQualifier
import dagger.hilt.android.components.ViewModelComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import kotlinx.coroutines.delay
import javax.inject.Inject

@BoundTo(supertype = OneInteractor::class, component = ViewModelComponent::class)
class OneInteractorImpl @Inject constructor(
    @AuthNetworkClientQualifier private val networkClient: NetworkClient
) : OneInteractor {
    override suspend fun makeSomething() {
        networkClient.request()
        Log.d("OneInteractor", "Something")
    }
}