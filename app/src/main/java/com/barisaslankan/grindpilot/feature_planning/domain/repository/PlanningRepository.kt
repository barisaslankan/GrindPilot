package com.barisaslankan.grindpilot.feature_planning.domain.repository

import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.feature_planning.data.local.entity.GoalEntity
import com.barisaslankan.grindpilot.feature_planning.data.local.entity.PlanEntity
import com.barisaslankan.grindpilot.feature_planning.domain.model.Goal
import com.barisaslankan.grindpilot.feature_planning.domain.model.Plan
import com.barisaslankan.grindpilot.feature_planning.domain.model.ProgressType
import com.barisaslankan.grindpilot.feature_planning.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface PlanningRepository {
    fun getGoals(): Flow<Resource<List<Goal>>>
    suspend fun updateGoals(): Resource<List<GoalEntity>>
    fun getPlans(): Flow<Resource<List<Plan>>>
    suspend fun updatePlans(): Resource<List<PlanEntity>>
    suspend fun createPlan(
        name: String,
        goals: List<Goal>,
        days : List<String>,
        duration : Double
    ) : Resource<Plan>
    suspend fun createGoal(
        name: String,
        progressType: ProgressType,
        tasks: List<Task>?,
        workTime: String,
        totalWork: Double
    ) : Resource<Goal>
    suspend fun updateGoalProgress(goalId : String, current : Double) : Resource<Boolean>
}