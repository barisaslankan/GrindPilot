package com.barisaslankan.grindpilot.feature.auth.data.repository

import com.barisaslankan.grindpilot.core.mappers.toUser
import com.barisaslankan.grindpilot.core.util.DB_USERS
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.core.util.await
import com.barisaslankan.grindpilot.feature.auth.domain.repository.AuthRepository
import com.barisaslankan.grindpilot.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDb: FirebaseFirestore,
): AuthRepository {

    val user = firebaseAuth.currentUser
    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String,
    ): Resource<User> {
        return try{
            val result = firebaseAuth.signInWithEmailAndPassword(email,password).await()
            Resource.Success(result.user!!.toUser())
        }catch (e: Exception){
            Resource.Error("Operation Failed: ${e.localizedMessage}")
        }
    }

    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        repeatPassword : String
    ) : Resource<User>{
        return try{
            if(repeatPassword != password) return Resource.Error("Passwords don't match")
            val result = firebaseAuth.createUserWithEmailAndPassword(email,password).await()
            Resource.Success(result.user!!.toUser())
        }catch (e : Exception){
            Resource.Error("Operation Failed: ${e.localizedMessage}")
        }
    }

    override suspend fun addUserToDb(user: User): Resource<User> {
        return try{
            firebaseDb.collection(DB_USERS).document(user.userId).set(user).await()
            Resource.Success(data = user)
        }catch(e:Exception){
            Resource.Error(e.localizedMessage)
        }
    }

    override suspend fun getUserFromDb(): Resource<User> {
        return try{
            val documentSnapshot = firebaseDb.collection(DB_USERS).document(user!!.uid).get().await()
            val id = documentSnapshot.getString("userId")!!
            val email = documentSnapshot.getString("email")!!
            val name = documentSnapshot.getString("name")

            val user = User(id, email, name)
            Resource.Success(data = user)
        }catch(e:Exception){
            Resource.Error(e.localizedMessage)
        }
    }
}

