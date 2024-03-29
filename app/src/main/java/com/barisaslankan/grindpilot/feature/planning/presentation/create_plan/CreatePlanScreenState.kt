package com.barisaslankan.grindpilot.feature.planning.presentation.create_plan

import com.barisaslankan.grindpilot.model.Goal

data class CreatePlanScreenState(
    val isLoading : Boolean = false,
    val goals : ArrayList<Goal> = arrayListOf(),
    val name : String = "",
    val selectedGoals : ArrayList<Goal> = arrayListOf(),
    val error : String? = null
)
