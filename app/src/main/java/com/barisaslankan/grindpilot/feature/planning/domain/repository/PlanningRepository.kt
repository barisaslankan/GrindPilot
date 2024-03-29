package com.barisaslankan.grindpilot.feature.planning.domain.repository

import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.model.Goal
import com.barisaslankan.grindpilot.model.Plan
import kotlinx.coroutines.flow.Flow

interface PlanningRepository {
    fun fetchGoals() : Flow<Resource<ArrayList<Goal>>>
    fun fetchPlans() : Flow<Resource<ArrayList<Plan>>>
    suspend fun uploadPlan(plan : Plan) : Resource<Plan>
}