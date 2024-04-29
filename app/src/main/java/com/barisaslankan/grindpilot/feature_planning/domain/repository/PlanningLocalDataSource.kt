package com.barisaslankan.grindpilot.feature_planning.domain.repository

import com.barisaslankan.grindpilot.feature_planning.data.local.entity.GoalEntity
import com.barisaslankan.grindpilot.feature_planning.data.local.entity.PlanEntity
import kotlinx.coroutines.flow.Flow

interface PlanningLocalDataSource {
    fun getAllGoals() : Flow<List<GoalEntity>>
    suspend fun upsertGoals(goals : List<GoalEntity>)
    suspend fun clearGoals()
    fun getAllPlans() : Flow<List<PlanEntity>>
    suspend fun upsertPlans(plans : List<PlanEntity>)
    suspend fun clearPlans()
    suspend fun getGoalsByIds(goalIds: List<String>): List<GoalEntity>
    suspend fun updateGoalProgress(goadId : String, current : Double)
    suspend fun getGoalById(goalId : String) : GoalEntity
    suspend fun insertGoal(goal : GoalEntity)
}
