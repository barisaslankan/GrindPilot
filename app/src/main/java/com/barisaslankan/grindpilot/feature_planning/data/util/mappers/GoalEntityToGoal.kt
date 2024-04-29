package com.barisaslankan.grindpilot.feature_planning.data.util.mappers

import com.barisaslankan.grindpilot.feature_planning.data.local.entity.GoalEntity
import com.barisaslankan.grindpilot.feature_planning.domain.model.Goal

fun GoalEntity.toGoal(ownerId : String) : Goal {
    return Goal(
        id = id,
        ownerId = ownerId,
        name = name,
        progressType = progressType,
        tasks = tasks?.let { ArrayList(it) },
        current = current,
        workTime = workTime,
        totalWork = totalWork
    )
}