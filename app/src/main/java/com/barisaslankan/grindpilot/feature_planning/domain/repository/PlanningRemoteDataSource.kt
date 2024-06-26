package com.barisaslankan.grindpilot.feature_planning.domain.repository

import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.feature_planning.domain.model.Goal
import com.barisaslankan.grindpilot.feature_planning.domain.model.Plan
import com.barisaslankan.grindpilot.feature_planning.domain.model.ProgressType
import com.barisaslankan.grindpilot.feature_planning.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface PlanningRemoteDataSource {
    fun fetchGoals() : Flow<Resource<ArrayList<Goal>>>
    fun fetchPlans() : Flow<Resource<ArrayList<Plan>>>
    suspend fun createPlan(
        id: String,
        name: String,
        goals: List<Goal>,
        days : List<String>,
        planDuration : Double
    ) : Resource<Plan>
    suspend fun createGoal(
        id : String,
        name: String,
        progressType: ProgressType,
        tasks: List<Task>,
        workTime: String,
        totalWork: Double) : Resource<Goal>
    suspend fun updateGoal(goal : Goal) : Resource<Goal>
}