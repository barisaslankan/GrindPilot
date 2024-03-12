package com.barisaslankan.grindpilot.feature.auth.repository

import com.barisaslankan.grindpilot.core.util.DB_USERS
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.core.util.await
import com.barisaslankan.grindpilot.model.User
import com.google.android.gms.auth.api.identity.SignInPassword
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDb: FirebaseFirestore,
): AuthRepository {
    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ): Resource<FirebaseUser> {
        return try{
            val result = firebaseAuth.signInWithEmailAndPassword(email,password).await()
            Resource.Success(result.user!!)
        }catch (e: Exception){
            Resource.Error("Operation Failed: ${e.localizedMessage}" ?: "Something went wrong!")
        }
    }

    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        repeatPassword : String
    ) : Resource<FirebaseUser>{
        return try{
            if(repeatPassword != password) return Resource.Error("Passwords don't match")
            val result = firebaseAuth.createUserWithEmailAndPassword(email,password).await()
            try {
                val user = User(userId = result.user!!.uid, email = result.user!!.email.toString(), name =  "")
                firebaseDb.collection(DB_USERS).document(result.user!!.uid).set(user).await()
            } catch (firestoreException: Exception) {
                return Resource.Error("Operation failed: ${firestoreException.localizedMessage}")
            }
            Resource.Success(result.user!!)
        }catch (e : Exception){
            Resource.Error("Operation Failed: ${e.localizedMessage}" ?: "Something went wrong!")
        }
    }


    private suspend fun addUserToFirestore(firebaseUser: FirebaseUser) {
        val user = User(firebaseUser.uid, firebaseUser.email.toString(), "")
        firebaseDb.collection(DB_USERS).document(firebaseUser.uid).set(user).await()
    }

}

