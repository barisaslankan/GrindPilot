package com.barisaslankan.grindpilot.feature_planning.presentation.set_goal

import com.barisaslankan.grindpilot.feature_planning.domain.model.Goal
import com.barisaslankan.grindpilot.feature_planning.domain.model.Plan
import com.barisaslankan.grindpilot.feature_planning.domain.model.ProgressType
import com.barisaslankan.grindpilot.feature_planning.domain.model.Task

data class SetGoalState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val plan: Plan? = null,
    val goal: Goal? = null,
    val goalName: String = "",
    val progressType: ProgressType = ProgressType.HOURS,
    val workTime: String = "",
    val totalWork: Double = 0.0,
    val tasks: List<Task> = emptyList(),
    val isProgressTypeExpanded: Boolean = false,
    val task: Task = Task(),
    val displayedProgressType: String = ProgressType.HOURS.name.substring(0, 1) + ProgressType.HOURS.name.substring(1).lowercase(),
    val displayTaskDialog: Boolean = false,
    val taskText: String = ""
    )
