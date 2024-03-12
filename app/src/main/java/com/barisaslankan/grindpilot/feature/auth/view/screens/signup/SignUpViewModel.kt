package com.barisaslankan.grindpilot.feature.auth.view.screens.signup

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
class SignUpViewModel @Inject constructor(
    private val authRepository : AuthRepository
) : ViewModel() {

    private val _state = mutableStateOf(SignUpScreenState())
    val state : State<SignUpScreenState> = _state

    fun createUserWithEmailAndPassword(email: String, password: String, repeatPassword : String){
        viewModelScope.launch {
            _state.value = SignUpScreenState(isLoading = true)
            val result = authRepository.createUserWithEmailAndPassword(email, password, repeatPassword)
            when(result){
                is Resource.Error -> {
                    _state.value = SignUpScreenState(isLoading = false, error = result.message ?: "Something went wrong!")
                }
                is Resource.Loading -> {
                    _state.value = SignUpScreenState(isLoading = true, error = "")
                }
                is Resource.Success -> {
                    _state.value = SignUpScreenState(user = result.data!!.toUser(), isLoading = false, error = "")
                }
            }
        }
    }

}