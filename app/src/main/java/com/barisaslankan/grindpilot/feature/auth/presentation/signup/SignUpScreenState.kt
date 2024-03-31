package com.barisaslankan.grindpilot.feature.auth.presentation.signup

import com.barisaslankan.grindpilot.model.User

data class SignUpScreenState(
    val isLoading : Boolean = false,
    val error : String? = null,
    val user : User? = null,
    val userFromDb : Boolean = false,
    val email : String = "",
    val password : String = "",
    val repeatPassword : String = ""
)