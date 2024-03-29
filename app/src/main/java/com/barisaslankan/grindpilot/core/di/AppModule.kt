package com.barisaslankan.grindpilot.core.di

import com.barisaslankan.grindpilot.feature.calendar.domain.repository.CalendarRepository
import com.barisaslankan.grindpilot.feature.calendar.data.repository.CalendarRepositoryImpl
import com.barisaslankan.grindpilot.feature.planning.domain.repository.PlanningRepository
import com.barisaslankan.grindpilot.feature.planning.data.repository.PlanningRepositoryImpl
import com.barisaslankan.grindpilot.feature.set_goal.domain.repository.SetGoalRepository
import com.barisaslankan.grindpilot.feature.set_goal.data.repository.SetGoalRepositoryImpl
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
    fun provideCalendarRepository(
        firebaseFirestore: FirebaseFirestore,
        firebaseAuth: FirebaseAuth
    ) : CalendarRepository {
        return CalendarRepositoryImpl(
            auth = firebaseAuth,
            db = firebaseFirestore
        )
    }

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

    @Provides
    @Singleton
    fun providePlanningRepository(
        firebaseFirestore: FirebaseFirestore,
        firebaseAuth: FirebaseAuth
    ) : PlanningRepository {
        return PlanningRepositoryImpl(
            auth = firebaseAuth,
            db = firebaseFirestore
        )
    }
}