package com.barisaslankan.grindpilot.feature_auth.presentation.login

import com.barisaslankan.grindpilot.feature_auth.domain.model.User

data class LoginScreenState(
    val isLoading : Boolean = false,
    val error : String? = null,
    val user : User? = null,
    val userFromDb : Boolean = false,
    val email : String = "",
    val password : String = ""
)
