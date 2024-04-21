package com.barisaslankan.grindpilot.feature.planning.data.util.mappers

import com.barisaslankan.grindpilot.core.model.Goal
import com.barisaslankan.grindpilot.core.model.ProgressType
import com.barisaslankan.grindpilot.feature.planning.data.local.entity.GoalEntity

fun Goal.toGoalEntity() : GoalEntity{
    return GoalEntity(
        id = id,
        ownerId = ownerId,
        name = name,
        progressType = progressType,
        tasks = tasks,
        progress = progress,
        workTime = workTime,
        totalWork = totalWork
    )
}