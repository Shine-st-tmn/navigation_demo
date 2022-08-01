package com.shint_st.feature_one.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthNetworkClientQualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SimpleNetworkClientQualifier