package com.barisaslankan.grindpilot.feature.auth.view.screens.welcome

import com.barisaslankan.grindpilot.model.User

data class WelcomeScreenState (
    val isLoading : Boolean = false,
    val error : String? = "",
    val user : User? = null
)
