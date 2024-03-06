package com.barisaslankan.grindpilot.feature.auth

import android.content.Intent
import com.barisaslankan.grindpilot.core.util.Resource
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun createUserWithEmail(email : String, password : String) : Resource<FirebaseUser>
    suspend fun loginWithGoogle(data: Intent?) : Resource<FirebaseUser>

}