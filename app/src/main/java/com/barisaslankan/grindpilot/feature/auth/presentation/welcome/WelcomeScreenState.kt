package com.barisaslankan.grindpilot.feature.auth.presentation.welcome

import com.barisaslankan.grindpilot.core.model.User

data class WelcomeScreenState (
    val isLoading : Boolean = false,
    val error : String? = null,
    val user : User? = null,
    val userFromDb : Boolean = false,
)
