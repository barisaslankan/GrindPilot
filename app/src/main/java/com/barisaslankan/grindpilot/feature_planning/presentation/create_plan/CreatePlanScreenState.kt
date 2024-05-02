package com.barisaslankan.grindpilot.feature_planning.presentation.create_plan

import com.barisaslankan.grindpilot.feature_planning.domain.model.Day
import com.barisaslankan.grindpilot.feature_planning.domain.model.DurationType
import com.barisaslankan.grindpilot.feature_planning.domain.model.Goal

data class CreatePlanScreenState(
    val isLoading : Boolean = false,
    val goals : ArrayList<Goal> = arrayListOf(),
    val name : String = "",
    val selectedGoals : ArrayList<Goal> = arrayListOf(),
    val planDuration : Double = 0.0,
    val error : String? = null,
    val durationType: DurationType = DurationType.WEEKS,
    val isDurationTypeExpanded : Boolean = false,
    val durationText : String = "",
    val displayedDurationType: String = DurationType.WEEKS.name.substring(0, 1) + DurationType.WEEKS.name.substring(1).lowercase(),
    val selectedDays : List<Day> = emptyList(),
    val isBottomSheetExpanded : Boolean = false,
    val displayedTaskWeight : String = "",
    val isTaskWeightPickerExpanded : Boolean = false
)
