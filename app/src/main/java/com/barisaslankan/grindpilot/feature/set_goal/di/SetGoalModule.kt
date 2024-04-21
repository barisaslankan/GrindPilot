package com.barisaslankan.grindpilot.feature.set_goal.di

import com.barisaslankan.grindpilot.feature.set_goal.data.repository.SetGoalRepositoryImpl
import com.barisaslankan.grindpilot.feature.set_goal.domain.repository.SetGoalRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SetGoalModule {

    @Provides
    @Singleton
    fun provideSetGoalRepository(
        firebaseFirestore: FirebaseFirestore,
        firebaseAuth: FirebaseAuth
    ) : SetGoalRepository {
        return SetGoalRepositoryImpl(
            firebaseAuth = firebaseAuth,
            db = firebaseFirestore
        )
    }
}