package com.barisaslankan.grindpilot.feature.auth.presentation.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisaslankan.grindpilot.core.mappers.toUser
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.feature.auth.domain.repository.AuthRepository
import com.barisaslankan.grindpilot.feature.auth.presentation.welcome.WelcomeScreenState
import com.barisaslankan.grindpilot.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository : AuthRepository
) : ViewModel() {

    private val _state = mutableStateOf(SignUpScreenState())
    val state : State<SignUpScreenState> = _state

    init {
        getUserFromDb()
    }

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
                    _state.value = SignUpScreenState(user = result.data!!, isLoading = false, error = "")
                }
            }
        }
    }
    fun addUserToDb(user : User){
        viewModelScope.launch {
            _state.value = SignUpScreenState(isLoading = true)
            val result = authRepository.addUserToDb(user)
            when(result){
                is Resource.Error -> {
                    _state.value = SignUpScreenState(isLoading = false, error = result.message ?: "Something went wrong!")
                }
                is Resource.Loading -> {
                    _state.value = SignUpScreenState(isLoading = true, error = null)
                }
                is Resource.Success -> {
                    _state.value = SignUpScreenState(user = result.data, userFromDb = true, isLoading = false, error = null)
                }
            }
        }
    }

    private fun getUserFromDb(){
        viewModelScope.launch{
            _state.value = SignUpScreenState(isLoading = true)
            val result = authRepository.getUserFromDb()
            when(result){
                is Resource.Error -> {
                    _state.value = SignUpScreenState(isLoading = false, error = result.message ?: "Something went wrong!")
                }
                is Resource.Loading -> {
                    _state.value = SignUpScreenState(isLoading = true, error = null)
                }
                is Resource.Success -> {
                    _state.value = SignUpScreenState(user = result.data, userFromDb = true, isLoading = false, error = null)
                }
            }
        }
    }

    fun onEmailChanged(email: String){
        _state.value = SignUpScreenState(email = email)
    }
    fun onPasswordChanged(password: String){
        _state.value = SignUpScreenState(password = password)
    }
    fun onRepeatPasswordChanged(repeatPassword: String){
        _state.value = SignUpScreenState(repeatPassword = repeatPassword)
    }
}