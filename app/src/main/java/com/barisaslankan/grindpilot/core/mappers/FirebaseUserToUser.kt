package com.barisaslankan.grindpilot.core.mappers

import com.barisaslankan.grindpilot.model.User
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toUser(): User{
    return User(
        userId = uid,
        name = displayName,
        email = email ?: ""
    )
}
