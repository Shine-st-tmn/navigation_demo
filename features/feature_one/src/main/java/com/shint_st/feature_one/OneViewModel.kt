package com.shint_st.feature_one

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shint_st.feature_one.domain.OneInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OneViewModel @Inject constructor(
    private val state: SavedStateHandle,
    private val interactor: OneInteractor
) : ViewModel() {
    fun makeSomething() = viewModelScope.launch {
        interactor.makeSomething()
    }
}