package com.barisaslankan.grindpilot.feature_auth.domain.model

data class User(
    val userId : String = "",
    val email : String = "",
    val name : String? = null
)


