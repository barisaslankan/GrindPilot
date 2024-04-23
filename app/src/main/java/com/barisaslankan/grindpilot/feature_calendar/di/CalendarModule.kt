package com.barisaslankan.grindpilot.feature_calendar.di

import com.barisaslankan.grindpilot.feature_calendar.data.repository.CalendarRepositoryImpl
import com.barisaslankan.grindpilot.feature_calendar.domain.repository.CalendarRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CalendarModule {
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
}