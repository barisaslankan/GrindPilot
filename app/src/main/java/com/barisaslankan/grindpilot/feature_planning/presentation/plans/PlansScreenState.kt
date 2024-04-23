package com.barisaslankan.grindpilot.feature_planning.presentation.plans

import com.barisaslankan.grindpilot.feature_planning.domain.model.Plan

data class PlansScreenState(
    val isLoading : Boolean = false,
    val plans : ArrayList<Plan>? = arrayListOf(),
    val error : String? = null
    )
