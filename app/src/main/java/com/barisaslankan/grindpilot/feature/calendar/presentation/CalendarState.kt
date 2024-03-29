package com.barisaslankan.grindpilot.feature.calendar.presentation

import com.barisaslankan.grindpilot.model.Goal
import com.barisaslankan.grindpilot.model.Plan

data class CalendarState(
    val isLoading : Boolean = false,
    val error : String? = null,
    val plans : ArrayList<Plan>? = null,
    val goals : ArrayList<Goal>? = null
)
