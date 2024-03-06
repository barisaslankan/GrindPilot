package com.barisaslankan.grindpilot.feature.auth.di

import com.barisaslankan.grindpilot.feature.auth.AuthRepository
import com.barisaslankan.grindpilot.feature.auth.AuthRepositoryImpl
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() : FirebaseAuth{
        return Firebase.auth
    }

    @Provides
    @Singleton
    fun provideFirestore() : FirebaseFirestore{
        return Firebase.firestore
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseFirestore: FirebaseFirestore,
        firebaseAuth: FirebaseAuth
    ) : AuthRepository{
        return AuthRepositoryImpl(
            firebaseAuth = firebaseAuth,
            firebaseDb = firebaseFirestore
        )
    }

}