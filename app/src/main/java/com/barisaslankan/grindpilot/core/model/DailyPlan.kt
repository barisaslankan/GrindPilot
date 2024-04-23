package com.barisaslankan.grindpilot.core.model

import com.barisaslankan.grindpilot.feature_planning.domain.model.Goal

data class DailyPlan(
    val id : String,
    val ownerId : String,
    val dailyWorks : ArrayList<Goal>?,
    val generalProgress : Double,
)
