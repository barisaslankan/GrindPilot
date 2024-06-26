package com.barisaslankan.grindpilot.feature_auth.presentation.welcome

import com.barisaslankan.grindpilot.feature_auth.domain.model.User

data class WelcomeScreenState (
    val isLoading : Boolean = false,
    val error : String? = null,
    val user : User? = null,
    val userFromDb : Boolean = false,
)
