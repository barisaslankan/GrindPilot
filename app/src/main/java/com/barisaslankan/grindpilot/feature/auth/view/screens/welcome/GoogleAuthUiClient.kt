package com.barisaslankan.grindpilot.feature.auth.view.screens.welcome

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.barisaslankan.grindpilot.BuildConfig
import com.barisaslankan.grindpilot.core.util.DB_USERS
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.core.util.await
import com.barisaslankan.grindpilot.feature.auth.repository.AuthRepository
import com.barisaslankan.grindpilot.model.User
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class GoogleAuthUiClient(
    private val context: Context,
    private val oneTapClient: SignInClient
) {
    private val auth = Firebase.auth
    private val firebaseDb = Firebase.firestore

    suspend fun signIn(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await()
        } catch(e: Exception) {
            e.printStackTrace()
            if(e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    suspend fun signInWithIntent(intent: Intent): Resource<User?> {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val userResult = auth.signInWithCredential(googleCredentials).await().user
            try {
                val user = User(userResult!!.uid, userResult.email.toString(), "")
                firebaseDb.collection(DB_USERS).document(userResult.uid).set(user).await()
            } catch (firestoreException: Exception) {
                return Resource.Error("Operation failed: ${firestoreException.localizedMessage}")
            }
            Resource.Success(
                data = userResult.run {
                    User(
                        userId = uid,
                        email = email.toString(),
                        name = displayName
                    )
                },
            )
        } catch(e: Exception) {
            e.printStackTrace()
            if(e is CancellationException) throw e
            Resource.Error(
                data = null,
                message = e.message ?: "Something went wrong!"
            )
        }
    }

    suspend fun signOut() {
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch(e: Exception) {
            e.printStackTrace()
            if(e is CancellationException) throw e
        }
    }

    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(BuildConfig.WEB_CLIENT_ID)
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
}