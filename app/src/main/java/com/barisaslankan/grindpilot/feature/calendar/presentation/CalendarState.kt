package com.barisaslankan.grindpilot.feature.calendar.presentation

import com.barisaslankan.grindpilot.core.model.Goal
import com.barisaslankan.grindpilot.core.model.Plan

data class CalendarState(
    val isLoading : Boolean = false,
    val error : String? = null,
    val plans : ArrayList<Plan>? = null,
    val goals : ArrayList<Goal>? = null
)
