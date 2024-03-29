package com.barisaslankan.grindpilot.feature.auth.presentation.welcome

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.feature.auth.domain.repository.AuthRepository
import com.barisaslankan.grindpilot.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _state = mutableStateOf(WelcomeScreenState())
    val state : State<WelcomeScreenState> = _state

    init {
        getUserFromDb()
    }

    fun onSignInResult(result : Resource<User?>){
        when(result){
            is Resource.Error -> {
                _state.value = WelcomeScreenState(isLoading = false, error = result.message ?: "Something went wrong!")
            }
            is Resource.Loading -> {
                _state.value = WelcomeScreenState(isLoading = true, error = null)
            }
            is Resource.Success -> {
                _state.value = WelcomeScreenState(user = result.data, isLoading = false, error = null)
            }
        }
    }

    fun resetState() {
        _state.value = WelcomeScreenState()
    }

    fun addUserToDb(user : User){
        viewModelScope.launch {
            _state.value = WelcomeScreenState(isLoading = true)
            val result = authRepository.addUserToDb(user)
            when(result){
                is Resource.Error -> {
                    _state.value = WelcomeScreenState(isLoading = false, error = result.message ?: "Something went wrong!")
                }
                is Resource.Loading -> {
                    _state.value = WelcomeScreenState(isLoading = true, error = null)
                }
                is Resource.Success -> {
                    _state.value = WelcomeScreenState(user = result.data, userFromDb = true, isLoading = false, error = null)
                }
            }
        }
    }

    private fun getUserFromDb(){
        viewModelScope.launch{
            _state.value = WelcomeScreenState(isLoading = true)
            val result = authRepository.getUserFromDb()
            when(result){
                is Resource.Error -> {
                    _state.value = WelcomeScreenState(isLoading = false, error = result.message ?: "Something went wrong!")
                }
                is Resource.Loading -> {
                    _state.value = WelcomeScreenState(isLoading = true, error = null)
                }
                is Resource.Success -> {
                    _state.value = WelcomeScreenState(user = result.data, userFromDb = true, isLoading = false, error = null)
                }
            }
        }
    }
}