package com.barisaslankan.grindpilot.feature.auth

import android.app.Activity
import android.content.Intent
import com.barisaslankan.grindpilot.BuildConfig
import com.barisaslankan.grindpilot.core.util.DB_USERS
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.core.util.await
import com.barisaslankan.grindpilot.model.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDb: FirebaseFirestore,
    private val activity: Activity
): AuthRepository {

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(BuildConfig.WEB_CLIENT_ID)
        .requestEmail()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(activity, gso)

    override suspend fun createUserWithEmail(email: String, password: String) : Resource<FirebaseUser>{
        return try{
            val result = firebaseAuth.createUserWithEmailAndPassword(email,password).await()
            try {
                addUserToFirestore(result.user!!)
            } catch (firestoreException: Exception) {
                return Resource.Error("Operation failed: ${firestoreException.localizedMessage}")
            }
            Resource.Success(result.user!!)
        }catch (e : Exception){
            Resource.Error("Operation Failed: ${e.localizedMessage}" ?: "Something went wrong!")
        }
    }

    override suspend fun loginWithGoogle(data: Intent?) : Resource<FirebaseUser> {
        val signInIntent = googleSignInClient.signInIntent

        val task = GoogleSignIn.getSignedInAccountFromIntent(data)

        return try {
            val account = task.getResult(ApiException::class.java)
            val firebaseAuthResult = firebaseAuthWithGoogle(account)
            Resource.Success(firebaseAuthResult.user!!)

        } catch (e: ApiException) {
            Resource.Error(e.localizedMessage ?: "Google Sign-In error")
        }

    }

    private suspend fun addUserToFirestore(firebaseUser: FirebaseUser) {
        val user = User(firebaseUser.uid, firebaseUser.email.toString(), "")
        firebaseDb.collection(DB_USERS).document(firebaseUser.uid).set(user).await()
    }

    private suspend fun firebaseAuthWithGoogle(account: GoogleSignInAccount?): AuthResult {
        val googleCredential = GoogleAuthProvider.getCredential(account?.idToken, null)
        return firebaseAuth.signInWithCredential(googleCredential).await()
    }

}

