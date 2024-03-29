package com.barisaslankan.grindpilot.feature.auth.domain.repository

import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.model.User

interface AuthRepository {
    suspend fun signInWithEmailAndPassword(email : String, password : String) : Resource<User>
    suspend fun createUserWithEmailAndPassword(email : String, password : String, repeatPassword : String) : Resource<User>
    suspend fun addUserToDb(user : User) : Resource<User>
    suspend fun getUserFromDb() : Resource<User>
}