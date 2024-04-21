package com.barisaslankan.grindpilot.core.util.mappers

import com.barisaslankan.grindpilot.core.model.User
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toUser(): User {
    return User(
        userId = uid,
        name = displayName,
        email = email ?: ""
    )
}
