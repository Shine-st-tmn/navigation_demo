package com.shint_st.feature_one.data

import android.util.Log
import com.shint_st.feature_one.di.AuthNetworkClientQualifier
import com.shint_st.feature_one.di.SimpleNetworkClientQualifier
import dagger.hilt.android.components.ViewModelComponent
import it.czerwinski.android.hilt.annotations.BoundTo
import javax.inject.Inject

interface NetworkClient {
    fun request()
}

@SimpleNetworkClientQualifier
@BoundTo(supertype = NetworkClient::class, component = ViewModelComponent::class)
class SimpleNetworkClient @Inject constructor() : NetworkClient {
    override fun request() {
        Log.d("NetworkClient", "SimpleNetworkClient request")
    }
}

@AuthNetworkClientQualifier
@BoundTo(supertype = NetworkClient::class, component = ViewModelComponent::class)
class AuthNetworkClient @Inject constructor() : NetworkClient {
    override fun request() {
        Log.d("NetworkClient", "AuthNetworkClient request")
    }
}