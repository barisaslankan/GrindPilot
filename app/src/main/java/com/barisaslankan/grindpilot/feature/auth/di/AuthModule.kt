package com.barisaslankan.grindpilot.feature.auth.di

import com.barisaslankan.grindpilot.feature.auth.domain.repository.AuthRepository
import com.barisaslankan.grindpilot.feature.auth.data.repository.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
    fun provideAuthRepository(
        firebaseFirestore: FirebaseFirestore,
        firebaseAuth: FirebaseAuth
    ) : AuthRepository {
        return AuthRepositoryImpl(
            firebaseAuth = firebaseAuth,
            firebaseDb = firebaseFirestore
        )
    }

}