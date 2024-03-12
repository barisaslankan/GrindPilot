package com.barisaslankan.grindpilot.feature.auth.view.screens.signup

import com.barisaslankan.grindpilot.model.User

data class SignUpScreenState(
    val isLoading : Boolean = false,
    val error : String? = "",
    val user : User? = null,
    val email : String = "",
    val password : String = "",
    val repeatPassword : String = ""
)
