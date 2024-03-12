package com.barisaslankan.grindpilot.feature.auth.repository

import com.barisaslankan.grindpilot.core.util.Resource
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun signInWithEmailAndPassword(email : String, password : String) : Resource<FirebaseUser>
    suspend fun createUserWithEmailAndPassword(email : String, password : String, repeatPassword : String) : Resource<FirebaseUser>

}