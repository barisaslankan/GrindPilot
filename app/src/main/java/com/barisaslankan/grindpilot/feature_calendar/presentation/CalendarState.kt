package com.barisaslankan.grindpilot.feature_calendar.presentation

import com.barisaslankan.grindpilot.feature_planning.domain.model.Goal
import com.barisaslankan.grindpilot.feature_planning.domain.model.Plan

data class CalendarState(
    val isLoading : Boolean = false,
    val error : String? = null,
    val plans : ArrayList<Plan>? = null,
    val goals : ArrayList<Goal>? = null
)
