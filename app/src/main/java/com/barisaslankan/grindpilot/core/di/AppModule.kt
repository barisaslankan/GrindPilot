package com.barisaslankan.grindpilot.core.di

import android.content.Context
import com.barisaslankan.grindpilot.core.util.NetworkHelper
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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
    fun provideNetworkHelper(
        @ApplicationContext context: Context
    ) : NetworkHelper{
        return NetworkHelper(context)
    }

}