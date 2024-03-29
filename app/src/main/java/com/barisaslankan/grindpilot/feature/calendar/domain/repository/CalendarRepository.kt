package com.barisaslankan.grindpilot.feature.calendar.domain.repository

import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.model.Goal
import com.barisaslankan.grindpilot.model.Plan
import kotlinx.coroutines.flow.Flow

interface CalendarRepository {

    fun getPlans() : Flow<Resource<ArrayList<Plan>>>
    fun getGoals() : Flow<Resource<ArrayList<Goal>>>
    suspend fun uploadGoal(goal : Goal) : Resource<Goal>

}