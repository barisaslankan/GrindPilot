package com.barisaslankan.grindpilot.feature.planning.di

import android.content.Context
import androidx.room.Room
import com.barisaslankan.grindpilot.core.util.PLANNING_DATABASE
import com.barisaslankan.grindpilot.feature.planning.data.local.PlanningDatabase
import com.barisaslankan.grindpilot.feature.planning.data.repository.PlanningLocalDataSourceImpl
import com.barisaslankan.grindpilot.feature.planning.data.repository.PlanningRemoteDataSourceImpl
import com.barisaslankan.grindpilot.feature.planning.domain.repository.PlanningLocalDataSource
import com.barisaslankan.grindpilot.feature.planning.domain.repository.PlanningRemoteDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlanningModule {

    @Provides
    @Singleton
    fun providePlanningRoomDatabase(
        @ApplicationContext context: Context
    ) : PlanningDatabase {
        return Room.databaseBuilder(
            context,
            PlanningDatabase::class.java,
            PLANNING_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun providePlanningRemoteDataSource(
        firebaseFirestore: FirebaseFirestore,
        firebaseAuth: FirebaseAuth
    ) : PlanningRemoteDataSource {
        return PlanningRemoteDataSourceImpl(
            auth = firebaseAuth,
            db = firebaseFirestore
        )
    }

    @Provides
    @Singleton
    fun providePlanningLocalDataSource(
        database: PlanningDatabase
    ) : PlanningLocalDataSource {
        return PlanningLocalDataSourceImpl(
            planningDatabase = database
        )
    }

}