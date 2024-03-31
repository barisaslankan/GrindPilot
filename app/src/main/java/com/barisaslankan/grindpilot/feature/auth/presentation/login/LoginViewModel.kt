package com.barisaslankan.grindpilot.feature.auth.presentation.login

import android.util.Log
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
class LoginViewModel @Inject constructor(
    private val authRepository : AuthRepository
) : ViewModel() {

    private val _state = mutableStateOf(LoginScreenState())
    val state : State<LoginScreenState> = _state

    init {
        getUserFromDb()
    }

    fun signInWithEmailAndPassword(email: String, password: String){
        viewModelScope.launch {
            _state.value = LoginScreenState(isLoading = true)
            val result = authRepository.signInWithEmailAndPassword(email,password)
            when(result){
                is Resource.Error -> {
                    _state.value = LoginScreenState(isLoading = false, error = result.message)
                }
                is Resource.Success -> {
                    _state.value = LoginScreenState(user = result.data!!, isLoading = false, error = null)
                }
            }
        }
    }

    fun addUserToDb(user : User){
        viewModelScope.launch {
            _state.value = LoginScreenState(isLoading = true)
            val result = authRepository.addUserToDb(user)
            when(result){
                is Resource.Error -> {
                    _state.value = LoginScreenState(isLoading = false, error = result.message)
                }
                is Resource.Success -> {
                    _state.value = LoginScreenState(user = result.data, userFromDb = true, isLoading = false, error = null)
                }
            }
        }
    }

    private fun getUserFromDb(){
        viewModelScope.launch{
            _state.value = LoginScreenState(isLoading = true)
            val result = authRepository.getUserFromDb()
            when(result){
                is Resource.Error -> {
                    _state.value = LoginScreenState(isLoading = false, error = result.message)
                }
                is Resource.Success -> {
                    _state.value = LoginScreenState(user = result.data, userFromDb = true, isLoading = false, error = null)
                }
            }
        }
    }

    fun onEmailChanged(email : String){
        _state.value = LoginScreenState(email = email)
    }

    fun onPasswordChanged(password: String){
        _state.value = LoginScreenState(password = password)
    }

}