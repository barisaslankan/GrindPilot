package com.barisaslankan.grindpilot.feature.planning.domain.repository

import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.core.model.Goal
import com.barisaslankan.grindpilot.core.model.Plan
import kotlinx.coroutines.flow.Flow

interface PlanningRemoteDataSource {
    fun fetchGoals() : Flow<Resource<ArrayList<Goal>>>
    fun fetchPlans() : Flow<Resource<ArrayList<Plan>>>
    suspend fun uploadPlan(plan : Plan) : Resource<Plan>
}