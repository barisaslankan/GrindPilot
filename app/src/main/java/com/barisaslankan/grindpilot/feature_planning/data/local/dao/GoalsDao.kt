package com.barisaslankan.grindpilot.feature_planning.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.barisaslankan.grindpilot.core.util.GOALS_TABLE
import com.barisaslankan.grindpilot.feature_planning.data.local.entity.GoalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalsDao {
    @Query("SELECT * FROM $GOALS_TABLE")
    fun getAllGoals() : Flow<List<GoalEntity>>
    @Upsert
    suspend fun addGoals(goals : List<GoalEntity>)
    @Query("DELETE FROM $GOALS_TABLE")
    suspend fun deleteAllGoals()
    @Query("SELECT * FROM $GOALS_TABLE WHERE id IN (:goalIds)")
    suspend fun getGoalsByIds(goalIds: List<String>): List<GoalEntity>
}