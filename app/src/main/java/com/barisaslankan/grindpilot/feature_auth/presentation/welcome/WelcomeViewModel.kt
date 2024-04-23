package com.barisaslankan.grindpilot.feature_auth.presentation.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.feature_auth.domain.repository.AuthRepository
import com.barisaslankan.grindpilot.core.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(WelcomeScreenState())
    val state : StateFlow<WelcomeScreenState> = _state.asStateFlow()

    init {
        getUserFromDb()
    }

    fun onSignInResult(result : Resource<User?>){
        _state.update {
            it.copy(isLoading = true, error = null)
        }
        when(result){
            is Resource.Error -> {
                _state.update {
                    it.copy(isLoading = false, error = result.message)
                }
            }
            is Resource.Success -> {
                _state.update {
                    it.copy(user = result.data, isLoading = false, error = null)
                }
            }
        }
    }

    fun resetState() {
        _state.update {
            WelcomeScreenState()
        }
    }

    fun addUserToDb(user : User){
        viewModelScope.launch {
            _state.value = WelcomeScreenState(isLoading = true)
            val result = authRepository.addUserToDb(user)
            when(result){
                is Resource.Error -> {
                    _state.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
                is Resource.Success -> {
                    _state.update {
                        it.copy(user = result.data, userFromDb = true, isLoading = false, error = null)
                    }
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
                    _state.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }
                is Resource.Success -> {
                    _state.update {
                        it.copy(user = result.data, userFromDb = true, isLoading = false, error = null)
                    }
                }
            }
        }
    }

    fun isSignedInUser(): Boolean {
        return authRepository.isSignedInUser()
    }
}