package com.barisaslankan.grindpilot.feature.auth.view.screens.welcome

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.feature.auth.repository.AuthRepository
import com.barisaslankan.grindpilot.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = mutableStateOf(WelcomeScreenState())
    val state : State<WelcomeScreenState> = _state

    fun onSignInResult(result : Resource<User?>){
        when(result){
            is Resource.Error -> {
                _state.value = WelcomeScreenState(isLoading = false, error = result.message ?: "Something went wrong!")
            }
            is Resource.Loading -> {
                _state.value = WelcomeScreenState(isLoading = true, error = "")
            }
            is Resource.Success -> {
                _state.value = WelcomeScreenState(user = result.data, isLoading = false, error = "")
            }
        }
    }

    fun resetState() {
        _state.value = WelcomeScreenState()
    }
}