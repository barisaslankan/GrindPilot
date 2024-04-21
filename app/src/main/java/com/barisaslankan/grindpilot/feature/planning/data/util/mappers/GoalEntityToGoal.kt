package com.barisaslankan.grindpilot.feature.planning.data.util.mappers

import com.barisaslankan.grindpilot.core.model.Goal
import com.barisaslankan.grindpilot.feature.planning.data.local.entity.GoalEntity

fun GoalEntity.toGoal() : Goal {
    return Goal(
        id = id,
        ownerId = ownerId,
        name = name,
        progressType = progressType,
        tasks = tasks?.let { ArrayList(it) },
        progress = progress,
        workTime = workTime,
        totalWork = totalWork
    )
}