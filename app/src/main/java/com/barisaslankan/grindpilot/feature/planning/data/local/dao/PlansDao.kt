package com.barisaslankan.grindpilot.feature.planning.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.barisaslankan.grindpilot.core.util.GOALS_TABLE
import com.barisaslankan.grindpilot.core.util.PLANS_TABLE
import com.barisaslankan.grindpilot.feature.planning.data.local.entity.GoalEntity
import com.barisaslankan.grindpilot.feature.planning.data.local.entity.PlanEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlansDao {
    @Query("SELECT * FROM $PLANS_TABLE")
    fun getAllPlans() : Flow<List<PlanEntity>>
    @Upsert
    suspend fun addPlans(plans : List<PlanEntity>)
    @Query("DELETE FROM $PLANS_TABLE")
    suspend fun deleteAllPlans()
}