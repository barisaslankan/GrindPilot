package com.barisaslankan.grindpilot.feature.planning.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.barisaslankan.grindpilot.feature.planning.data.local.dao.GoalsDao
import com.barisaslankan.grindpilot.feature.planning.data.local.dao.PlansDao
import com.barisaslankan.grindpilot.feature.planning.data.local.entity.GoalEntity
import com.barisaslankan.grindpilot.feature.planning.data.local.entity.PlanEntity

@Database(entities = [GoalEntity::class, PlanEntity::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class PlanningDatabase() : RoomDatabase() {
    abstract fun goalsDao() : GoalsDao
    abstract fun plansDao() : PlansDao
}