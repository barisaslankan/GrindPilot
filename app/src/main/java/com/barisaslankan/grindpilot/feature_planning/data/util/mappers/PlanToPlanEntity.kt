package com.barisaslankan.grindpilot.feature_planning.data.util.mappers

import com.barisaslankan.grindpilot.feature_planning.data.local.entity.PlanEntity
import com.barisaslankan.grindpilot.feature_planning.domain.model.Plan

fun Plan.toPlanEntity() : PlanEntity{
    return PlanEntity(
        id = id,
        name = name,
        goals = goals.map { it.id },
        days = days
    )
}