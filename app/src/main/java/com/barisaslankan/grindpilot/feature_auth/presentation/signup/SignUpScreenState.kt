package com.barisaslankan.grindpilot.feature_auth.presentation.signup

import com.barisaslankan.grindpilot.core.model.User

data class SignUpScreenState(
    val isLoading : Boolean = false,
    val error : String? = null,
    val user : User? = null,
    val userFromDb : Boolean = false,
    val email : String = "",
    val password : String = "",
    val repeatPassword : String = ""
)
