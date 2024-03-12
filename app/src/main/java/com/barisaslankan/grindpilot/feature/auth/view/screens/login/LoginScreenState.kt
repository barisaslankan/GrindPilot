package com.barisaslankan.grindpilot.feature.auth.view.screens.login

import com.barisaslankan.grindpilot.model.User

data class LoginScreenState(
    val isLoading : Boolean = false,
    val error : String? = "",
    val user : User? = null,
    val email : String = "",
    val password : String = ""
)
