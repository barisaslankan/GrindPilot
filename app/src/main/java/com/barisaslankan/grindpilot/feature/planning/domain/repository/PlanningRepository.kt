package com.barisaslankan.grindpilot.feature.planning.domain.repository

import com.barisaslankan.grindpilot.core.model.Goal
import com.barisaslankan.grindpilot.core.model.Plan
import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.feature.planning.data.local.entity.GoalEntity
import com.barisaslankan.grindpilot.feature.planning.data.local.entity.PlanEntity
import kotlinx.coroutines.flow.Flow

interface PlanningRepository {
    fun getGoals(): Flow<Resource<List<Goal>>>
    suspend fun updateGoals(): Resource<List<GoalEntity>>
    fun getPlans(): Flow<Resource<List<Plan>>>
    suspend fun updatePlans(): Resource<List<PlanEntity>>
    suspend fun addPlan(plan : Plan) : Resource<Plan>
}