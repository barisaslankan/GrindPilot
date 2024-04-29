package com.barisaslankan.grindpilot.feature_auth.util.mappers

import com.barisaslankan.grindpilot.feature_auth.domain.model.User
import com.google.firebase.auth.FirebaseUser

fun FirebaseUser.toUser(): User {
    return User(
        userId = uid,
        name = displayName,
        email = email ?: ""
    )
}
