package com.barisaslankan.grindpilot.feature.auth.presentation.login

import com.barisaslankan.grindpilot.core.model.User

data class LoginScreenState(
    val isLoading : Boolean = false,
    val error : String? = null,
    val user : User? = null,
    val userFromDb : Boolean = false,
    val email : String = "",
    val password : String = ""
)
