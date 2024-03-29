package com.barisaslankan.grindpilot.feature.set_goal.domain.repository

import com.barisaslankan.grindpilot.core.util.Resource
import com.barisaslankan.grindpilot.model.Goal
import com.barisaslankan.grindpilot.model.Plan
import com.barisaslankan.grindpilot.model.ProgressType

interface SetGoalRepository {

    suspend fun createPlan(
        name : String,
        goals : ArrayList<Goal>
    ) : Resource<Plan>
    suspend fun createGoal(
        name : String,
        progressType : ProgressType,
        tasks : ArrayList<String>?,
        workTime : String,
        totalWork : Double
    ) : Resource<Goal>

}