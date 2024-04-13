package com.barisaslankan.grindpilot.feature.set_goal.presentation

import com.barisaslankan.grindpilot.model.Goal
import com.barisaslankan.grindpilot.model.Plan
import com.barisaslankan.grindpilot.model.ProgressType

data class SetGoalState(
    val isLoading : Boolean = false,
    val error : String? = null,
    val plan : Plan? = null,
    val goal : Goal? = null,
    val goalName : String = "",
    val progressType : ProgressType = ProgressType.HOURS,
    val workTime : String = "",
    val totalWork : Double = 0.0,
    val tasks : ArrayList<String> = arrayListOf(),
    val isProgressTypeExpanded : Boolean = false,
    val isTimePickerExpanded : Boolean = false,
    val task : String = "",
    val displayedProgressType : String = ProgressType.HOURS.name.substring(0, 1) + ProgressType.HOURS.name.substring(1).lowercase()
    )
