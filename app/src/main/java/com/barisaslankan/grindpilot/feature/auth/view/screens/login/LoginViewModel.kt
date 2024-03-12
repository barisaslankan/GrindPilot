package com.barisaslankan.grindpilot.feature.auth.view.screens.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisaslankan.grindpilot.core.mappers.toUser
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.feature.auth.repository.AuthRepository
import com.barisaslankan.grindpilot.feature.auth.view.screens.welcome.WelcomeScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository : AuthRepository
) : ViewModel() {

    private val _state = mutableStateOf(LoginScreenState())
    val state : State<LoginScreenState> = _state

    fun signInWithEmailAndPassword(email: String, password: String){
        viewModelScope.launch {
            _state.value = LoginScreenState(isLoading = true)
            val result = authRepository.signInWithEmailAndPassword(email,password)
            when(result){
                is Resource.Error -> {
                    _state.value = LoginScreenState(isLoading = false, error = result.message ?: "Something went wrong!")
                }
                is Resource.Loading -> {
                    _state.value = LoginScreenState(isLoading = true, error = "")
                }
                is Resource.Success -> {
                    _state.value = LoginScreenState(user = result.data!!.toUser(), isLoading = false, error = "")
                }
            }
        }
    }

}