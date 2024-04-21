package com.barisaslankan.grindpilot.feature.planning.presentation.plans

import com.barisaslankan.grindpilot.core.model.Plan

data class PlansScreenState(
    val isLoading : Boolean = false,
    val plans : ArrayList<Plan>? = arrayListOf(),
    val error : String? = null
    )
