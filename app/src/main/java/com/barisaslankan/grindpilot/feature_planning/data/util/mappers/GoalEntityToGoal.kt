package com.barisaslankan.grindpilot.feature_planning.data.util.mappers

import com.barisaslankan.grindpilot.feature_planning.domain.model.Goal
import com.barisaslankan.grindpilot.feature_planning.data.local.entity.GoalEntity

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