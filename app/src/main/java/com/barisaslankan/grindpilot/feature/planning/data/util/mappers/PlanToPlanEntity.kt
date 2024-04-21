package com.barisaslankan.grindpilot.feature.planning.data.util.mappers

import com.barisaslankan.grindpilot.core.model.Plan
import com.barisaslankan.grindpilot.feature.planning.data.local.entity.PlanEntity

fun Plan.toPlanEntity() : PlanEntity{
    return PlanEntity(
        id = id,
        ownerId = ownerId,
        name = name,
        goals = goals.map { it.id }
    )
}